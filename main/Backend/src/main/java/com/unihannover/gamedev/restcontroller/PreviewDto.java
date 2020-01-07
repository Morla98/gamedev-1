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
    public List<Double> progress;


    public PreviewDto(String collectorName) {
        /*
        this.names = new ArrayList<String>();
        this.sectriptions = new ArrayList<String>();
        */
        this.achievements = new ArrayList<Achievement>();
        this.progress = new ArrayList<Double>();
        this.collectorName = collectorName;
    }

    /**
     * Adds a new UserAchievement to the preview by adding 1 item to each List.
     *
     * @param name The UserAchievements name
     * @param description The UserAchievements description
     * @param progress The UserAchievements progress
     */
    public void addUserAchievement(Achievement a, double prog){
        this.achievements.add(a);
        this.progress.add((Double)prog);
    }

    /*
    public void addUserAchievement(String name, String description, double prog){
        this.names.add(a.getName());
        this.names.add(a.getDescription());
        this.progress.add((Double)prog);
    }
     */

}