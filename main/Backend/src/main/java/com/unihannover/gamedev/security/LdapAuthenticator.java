package com.unihannover.gamedev.security;

import com.unihannover.gamedev.LdapConfigParser;
import com.unihannover.gamedev.LdapConfiguration;
import org.springframework.stereotype.Service;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.Properties;
import java.util.regex.Pattern;

@Service
public class LdapAuthenticator {
    LdapConfiguration ldapConfiguration = null;

    public void loadConfig() {
        ldapConfiguration = LdapConfigParser.LdapJsonToObject();
    }

    public boolean performAuthentication(String email, String password) {

        // Validate user email (to be safe when inserting it into the search filter)
        if (!isEmailValid(email)) {
            return false;
        }

        // Load the configuration
        if (ldapConfiguration == null) {
            loadConfig();
        }

        // first create the service context
        DirContext serviceCtx = null;
        try {
            // use the service user to authenticate
            Properties serviceEnv = new Properties();
            serviceEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            serviceEnv.put(Context.PROVIDER_URL, ldapConfiguration.LDAP_URL);
            serviceEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
            serviceEnv.put(Context.SECURITY_PRINCIPAL, ldapConfiguration.LDAP_SERVICE_USER_DN);
            serviceEnv.put(Context.SECURITY_CREDENTIALS, ldapConfiguration.LDAP_SERVICE_USER_PW);
            serviceCtx = new InitialDirContext(serviceEnv);

            SearchControls sc = new SearchControls();
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

            // Construct the search filter
            String searchFilter = ldapConfiguration.LDAP_SEARCH_FILTER;
            searchFilter = searchFilter.replaceAll("#EMAIL", email);

            NamingEnumeration<SearchResult> results = serviceCtx.search(ldapConfiguration.LDAP_BASE, searchFilter, sc);

            if (results.hasMore()) {
                // get the users DN (distinguishedName) from the result
                SearchResult result = results.next();
                String distinguishedName = result.getNameInNamespace();

                System.out.printf("User has DN %s\n", distinguishedName);

                // attempt another authentication, now with the user
                Properties authEnv = new Properties();
                authEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
                authEnv.put(Context.PROVIDER_URL, ldapConfiguration.LDAP_URL);
                authEnv.put(Context.SECURITY_PRINCIPAL, distinguishedName);
                authEnv.put(Context.SECURITY_CREDENTIALS, password);
                new InitialDirContext(authEnv); // Throws an exception, if login incorrect

                System.out.println("Authentication successful");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serviceCtx != null) {
                try {
                    serviceCtx.close();
                } catch (NamingException e) {
                    e.printStackTrace();
                }
            }
        }
        System.err.println("Authentication failed");
        return false;
    }

    /**
     * Returns true iff the provided email is valid (in terms of RFC822)
     */
    private static boolean isEmailValid(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }
}
