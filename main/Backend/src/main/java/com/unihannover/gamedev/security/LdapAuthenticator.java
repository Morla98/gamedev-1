package com.unihannover.gamedev.security;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.springframework.stereotype.Service;

import com.unihannover.gamedev.LdapConfigParser;
import com.unihannover.gamedev.LdapConfiguration;

@Service
public class LdapAuthenticator {
	LdapConfiguration ldapConfiguration = null;
	
	public void loadConfig() {
		ldapConfiguration = LdapConfigParser.LdapJsonToObject();
	}
	public boolean performAuthentication(String email, String password) {
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

	            // attempt another authentication, now with the user
	            Properties authEnv = new Properties();
	            authEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	            authEnv.put(Context.PROVIDER_URL, ldapConfiguration.LDAP_URL);
	            authEnv.put(Context.SECURITY_PRINCIPAL, ldapConfiguration.LDAP_SERVICE_USER_DN);
	            authEnv.put(Context.SECURITY_CREDENTIALS, ldapConfiguration.LDAP_SERVICE_USER_PW);
	            new InitialDirContext(authEnv);

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
}
