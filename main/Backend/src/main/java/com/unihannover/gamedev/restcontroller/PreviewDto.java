package com.unihannover.gamedev.restcontroller;

import com.unihannover.gamedev.models.*;
import java.util.List;
import java.util.ArrayList;

/**
 * A data transfer object that represents a UserAchievement preview.
 *
 * @author Lukas Niehus
 */
public class PreviewDto{

    public String collectorName;

    /*
    public List<String> names;
    public List<String> descriptions;
     */

    public List<Achievement> achievements;
    public List<UserAchievement> uAchievements;


    public PreviewDto(String collectorName) {
        /*
        this.names = new ArrayList<String>();
        this.sectriptions = new ArrayList<String>();
        */
        this.achievements = new ArrayList<Achievement>();
        this.progress = new ArrayList<UserAchievement>();
        this.collectorName = collectorName;
    }

    /**
     * Adds a new UserAchievement to the preview by adding 1 item to each List.
     *
     * @param a The Achievement to transfer
     * @param ua The userAchievement to transfer
     */
    public void addUserAchievement(Achievement a, UserAchievement ua){
        this.achievements.add(a);
        this.progress.add(ua);
    }

    /*
    public void addUserAchievement(String name, String description, UserAchievement ua){
        this.names.add(a.getName());
        this.names.add(a.getDescription());
        this.progress.add(ua);
    }
     */

}