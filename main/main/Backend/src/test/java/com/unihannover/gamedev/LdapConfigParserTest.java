package com.unihannover.gamedev;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LdapConfigParserTest {

	@Test
	void testLdapJsonToObject() {
		LdapConfiguration ldapConfiguration = LdapConfigParser.LdapJsonToObject();
		System.out.println("-------------");
        System.out.println("Printing LdapConfig object with its attributes");
        System.out.println(ldapConfiguration.LDAP_URL);
        System.out.println(ldapConfiguration.LDAP_SERVICE_USER_DN);
        System.out.println(ldapConfiguration.LDAP_SERVICE_USER_PW);
        System.out.println(ldapConfiguration.LDAP_BASE);
        System.out.println(ldapConfiguration.LDAP_SEARCH_FILTER);
        System.out.println("-------------");
        
        assertEquals("ldap://ldap-server:389", ldapConfiguration.LDAP_URL);
        assertEquals("cn=admin,dc=example,dc=org", ldapConfiguration.LDAP_SERVICE_USER_DN);
        assertEquals("admin", ldapConfiguration.LDAP_SERVICE_USER_PW);
        assertEquals("dc=example,dc=org", ldapConfiguration.LDAP_BASE);
        assertEquals("(&(objectClass=posixAccount)(mail=#EMAIL)(gidNumber=500))", ldapConfiguration.LDAP_SEARCH_FILTER);
	}
	
}
