package com.unihannover.gamedev.restcontroller;

import java.util.List;
import java.util.ArrayList;

/**
 * A data transfer object that represents a UserAchievement preview.
 *
 * @author Lukas Niehus
 */
public class PreviewDto{

    public String collectorName;

    public String name0;
    public String description0;
    public float progress0;

    public String name1;
    public String description1;
    public float progress1;

    public String name2;
    public String description2;
    public float progress2;

    public String name3;
    public String description3;
    public float progress3;


    public PreviewDto(String collectorName) {
        this.collectorName = collectorName;
    }

    /**
     * Adds a new UserAchievement to the preview by adding 1 item to each List.
     *
     * @param name The UserAchievements name
     * @param description The UserAchievements description
     * @param progress The UserAchievements progress
     */
    public void addUserAchievement(String name, String description, float progress, int number){
        switch(number){
            case 0: {
                this.name0 = name;
                this.description0 = description;
                this.progress0 = progress;
                break;
            }
            case 1: {
                this.name1 = name;
                this.description1 = description;
                this.progress1 = progress;
                break;
            }
            case 2: {
                this.name2 = name;
                this.description2 = description;
                this.progress2 = progress;
                break;
            }
            case 3: {
                this.name3 = name;
                this.description3 = description;
                this.progress3 = progress;
                break;
            }
            default: {
                break;
            }
        }
    }

}