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
