package com.unihannover.gamedev.models;

import java.sql.Timestamp;

/**
 * Represents a collector
 */
public class Collector implements Model{

    private String id;
    private String name;
    private Timestamp lastSeen;
    private String token;

    // *** Autogenerated Setters & Getters ***

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Timestamp lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String toJSON(){
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"id\": \"" + this.id + "\", ");
        json.append("\"name\": \"" + name + "\",");
        json.append("\"token\": \"" + token + "\"");
        json.append("}");
        return json.toString();
    }
}
