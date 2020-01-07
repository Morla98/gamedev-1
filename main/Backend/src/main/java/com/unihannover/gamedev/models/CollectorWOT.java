package com.unihannover.gamedev.models;

/**
 * Represents a collector, that contains data used to determine Achievement progress.
 * A CollectorWOT (Without Timestamps) is used to transfer every information except the Timestamps.
 */
public class CollectorWOT {
    private String id;
    private String name;
    private String token;

    /**
     * Returns the id of a collector (primary key).
     *
     * @return The collector id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of a collector.
     * Usually only used to initialise the collector, since id is a primary key.
     *
     * @param id The id of a collector
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the collector name.
     *
     * @return The name of the collector
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the collector to a given value.
     *
     * @param name The new collector name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a Token used to verify access.
     *
     * @return The token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets a token to verify access.
     *
     * @param token The token
     */
    public void setToken(String token) {
        this.token = token;
    }
}
