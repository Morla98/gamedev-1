package com.unihannover.gamedev;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class LdapConfigParser {

	/**
	 * The configuration file path
	 */
	private static final String CONFIG_FILEPATH = "config/ldapConfig.json";

    public static LdapConfiguration LdapJsonToObject() {
        File file = new File(CONFIG_FILEPATH);
        try {
            LdapConfiguration configuration = (new ObjectMapper()).readValue(file, LdapConfiguration.class);

            // Debug: Print parsed configuration
            printConfig(configuration);

            return configuration;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Converts the LdapConfiguration objects to a JSON String
     *
     * @param LdapConfig
     * @return json string of LdapConfiguration object
     */
    public static String LdapConfigToJson(LdapConfiguration configuration) {
        // Creating Object of ObjectMapper define in Jakson-Api
        ObjectMapper Obj = new ObjectMapper();
        try {
            // get LdapConfig object as a json string
            return Obj.writeValueAsString(configuration);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Print the parsed configuration into the stdout.
     * TODO: Remove in production?!
     */
    private static void printConfig(LdapConfiguration configuration) {
        System.out.println("-------------");
        System.out.println("Printing LdapConfig object with its attributes");
        System.out.println(configuration.LDAP_URL);
        System.out.println(configuration.LDAP_SERVICE_USER_DN);
        System.out.println(configuration.LDAP_SERVICE_USER_PW);
        System.out.println(configuration.LDAP_BASE);
        System.out.println(configuration.LDAP_SEARCH_FILTER);
        System.out.println("-------------");
    }
}
