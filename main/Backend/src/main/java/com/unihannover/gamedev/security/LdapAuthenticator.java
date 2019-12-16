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

import com.unihannover.gamedev.Configuration;

/**
 *
 * @author Dominik Andrae
 */
@Service
public class LdapAuthenticator {
	public boolean performAuthentication(String email, String password) {

	    // first create the service context
	    DirContext serviceCtx = null;
	    try {
	        // use the service user to authenticate
	        Properties serviceEnv = new Properties();
	        serviceEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	        serviceEnv.put(Context.PROVIDER_URL, Configuration.LDAP_URL);
	        serviceEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
	        serviceEnv.put(Context.SECURITY_PRINCIPAL, Configuration.LDAP_SERVICE_USER_DN);
	        serviceEnv.put(Context.SECURITY_CREDENTIALS, Configuration.LDAP_SERVICE_USER_PW);
	        serviceCtx = new InitialDirContext(serviceEnv);

	        // we don't need all attributes, just let it get the identifying one
	        String[] attributeFilter = { Configuration.LDAP_IDENTIFYING_ATTRIBUTE };
	        SearchControls sc = new SearchControls();
	        sc.setReturningAttributes(attributeFilter);
	        sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

	        // Construct the search filter
			String searchFilter = Configuration.LDAP_SEARCH_FILTER;
			searchFilter = searchFilter.replaceAll("#EMAIL", email);

	        NamingEnumeration<SearchResult> results = serviceCtx.search(Configuration.LDAP_BASE, searchFilter, sc);

	        if (results.hasMore()) {
	            // get the users DN (distinguishedName) from the result
	            SearchResult result = results.next();
	            String distinguishedName = result.getNameInNamespace();

	            // attempt another authentication, now with the user
	            Properties authEnv = new Properties();
	            authEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	            authEnv.put(Context.PROVIDER_URL, Configuration.LDAP_URL);
	            authEnv.put(Context.SECURITY_PRINCIPAL, Configuration.LDAP_SERVICE_USER_DN);
	            authEnv.put(Context.SECURITY_CREDENTIALS, Configuration.LDAP_SERVICE_USER_PW);
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
