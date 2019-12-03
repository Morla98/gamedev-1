package com.unihannover.gamedev.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;

/**
 * Represents a collector
 */
@Entity
@Table(name = "collectors", schema = "main")
public class Collector {

    public Collector(){}
    public Collector(CollectorWOT cWOT){
        this.id = cWOT.getId();
        this.name = cWOT.getName();
        this.token = cWOT.getToken();
        this.lastSeen = new Timestamp(System.currentTimeMillis());
    }
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_seen")
    private Timestamp lastSeen;

    @Column(name = "token")
    @JsonIgnore
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
}
