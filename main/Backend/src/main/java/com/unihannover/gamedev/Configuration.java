package com.unihannover.gamedev;

public final class Configuration {

    /**
     * The LDAP URL
     */
    public static String LDAP_URL = "ldap://ldap-server:389";

    /**
     * The credentials for the service user
     */
    public static String LDAP_SERVICE_USER_DN = "cn=admin,dc=example,dc=org";
    public static String LDAP_SERVICE_USER_PW = "admin";

    /**
     * The name of the identifying user attribute
     */
    public static String LDAP_IDENTIFYING_ATTRIBUTE = "mail";

    /**
     * The groups gidNumber the users is required to be member of
     */
    public static String LDAP_GROUP_ID = "500";

    /**
     * Base to search in
     */
    public static String LDAP_BASE = "dc=example,dc=org";

    /**
     * The filter
     *
     *  #EMAIL gets replaced with the provided email
     */
    public static String LDAP_SEARCH_FILTER = String.format("(&(objectClass=posixAccount)(%s=#EMAIL)(gidNumber=%s))", LDAP_IDENTIFYING_ATTRIBUTE, LDAP_GROUP_ID);
}
