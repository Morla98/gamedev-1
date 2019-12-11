package com.unihannover.gamedev;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LdapConfigParser {
	public static LdapConfiguration LdapJsonToObject() {	
		File file = new File("config/ldapConfig.json");
		try {
			LdapConfiguration[] ldapConfigArr = new ObjectMapper().readValue(file, LdapConfiguration[].class);
			LdapConfiguration configuration = ldapConfigArr[0];
			//------just for testing, should be removed 
			System.out.println("-------------");
			System.out.println("Printing LdapConfig object with its attributes ");
			System.out.println(configuration.LDAP_URL);
			System.out.println(configuration.LDAP_SERVICE_USER_DN);
			System.out.println(configuration.LDAP_SERVICE_USER_PW);
			System.out.println(configuration.LDAP_IDENTIFYING_ATTRIBUTE);
			System.out.println(configuration.LDAP_GROUP_ID);
			System.out.println(configuration.LDAP_BASE);
			System.out.println(configuration.LDAP_SEARCH_FILTER);
			System.out.println("-------------");
			//--------
			return configuration;
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * converts the LdapConfiguration objects to Json String 
	 * @param LdapConfig
	 * @return json string of LdapConfiguration object 
	 */
	public static String LdapConfigToJson(LdapConfiguration configuration) {		
		// Creating Object of ObjectMapper define in Jakson-Api 
	    ObjectMapper Obj = new ObjectMapper();
			try { 
				// get LdapConfig object as a json string 
				String jsonStr = Obj.writeValueAsString(configuration); 
		        return jsonStr;
		        
		     } catch (IOException e) { 
		        e.printStackTrace(); 
		     }
			 return null;
	}
}
