package com.unihannover.gamedev.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "collectors", schema="collector")
public class Collector {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @ElementCollection
    private List<Achievement> achievements;
    @ElementCollection
    private List<User>  users;

    public Collector(long id, String name){
        this.id = id;
        this.name = name;
        this.achievements = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Achievement> getAchievements() {
        return this.achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }
}
