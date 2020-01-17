package com.unihannover.gamedev.services;

import com.unihannover.gamedev.component.achievements.AbstractAchievementComponent;
import com.unihannover.gamedev.models.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementRegistry {

    /**
     * Automatically collects all components who inherit from AbstractAchievementComponent
     */
    @Autowired
    private List<AbstractAchievementComponent> achievements;

    @Autowired
    private ConfigurationService configurationService;

    /**
     * Getter for achievement list
     */
    public List<AbstractAchievementComponent> getAchievementList() {

        // Apply the configuration to each achievement transparently
        Configuration config = configurationService.getConfig();
        for(AbstractAchievementComponent achievement : this.achievements) {
            achievement.applyConfiguration(config);
        }

        return this.achievements;
    }
}
