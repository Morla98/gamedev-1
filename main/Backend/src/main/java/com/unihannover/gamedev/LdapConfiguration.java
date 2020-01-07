package com.unihannover.gamedev;

public class LdapConfiguration {

    /**
     * The LDAP URL
     */
    public String LDAP_URL;

    /**
     * The credentials for the service user
     */
    public  String LDAP_SERVICE_USER_DN;
    public  String LDAP_SERVICE_USER_PW;

    /**
     * The name of the identifying user attribute
     */
    public String LDAP_IDENTIFYING_ATTRIBUTE;

    /**
     * The groups gidNumber the users is required to be member of
     */
    public String LDAP_GROUP_ID;

    /**
     * Base to search in
     */
    public String LDAP_BASE;

    /**
     * The filter
     *
     *  #EMAIL gets replaced with the provided email
     */
    public  String LDAP_SEARCH_FILTER;
}
