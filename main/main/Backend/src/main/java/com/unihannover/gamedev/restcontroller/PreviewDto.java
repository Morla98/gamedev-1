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
    public List<AchievementDto> achievements;
    public double average;


    public PreviewDto(String collectorName) {
        this.achievements = new ArrayList<AchievementDto>();
        this.collectorName = collectorName;
        this.average = 0;
    }

    /**
     * Adds a new UserAchievement to the preview by adding 1 item to each List.
     *
     * @param a The Achievement to transfer
     * @param ua The userAchievement to transfer
     */
    public void addUserAchievement(AchievementDto a){
        this.achievements.add(a);
    }

    public void setAverage(double avg) {
        this.average = avg;
    }
    /*
    public void addUserAchievement(String name, String description, UserAchievement ua){
        this.names.add(a.getName());
        this.names.add(a.getDescription());
        this.progress.add(ua);
    }
     */

}