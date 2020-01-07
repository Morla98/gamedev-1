package com.unihannover.gamedev.services;

import com.unihannover.gamedev.component.achievements.AbstractAchievementComponent;
import com.unihannover.gamedev.models.Model;
import com.unihannover.gamedev.models.User;
import com.unihannover.gamedev.models.UserAchievement;
import com.unihannover.gamedev.repositories.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserAchievementUpdaterService {

    @Autowired
    private MetricRepository metricRepository;

    @Autowired
    private HttpService httpService;

    @Autowired
    private AchievementRegistry achievementRegistry;

    /**
     * Update all users achievements
     */
    public void updateForAllUsers() {
        // Query all known users from main application (via API call /users/all)
        for (User u : httpService.getUsers()) {
            this.updateForUser(u.getEmail(), true);
        }
    }

    /**
     * Update a specific users achievements
     */
    public void updateForUser(String userEmail, boolean knownEnsured) {

        // UNDONE: Remove debug output
        System.out.printf("This is the user-achievement updater! Was called for user %s.\n", userEmail);

        // If we do not know for sure that user is known ...
        if (!knownEnsured) {
            // ... ask the main application (via API call)
            if (!httpService.isKnownUser(userEmail)) {
                System.out.printf("User with email '%s' not known!\n", userEmail);
                return;
            }
        }

        List<Model> list = new ArrayList<>();

        // For each achievement
        for (AbstractAchievementComponent achievement : achievementRegistry.getAchievementList()) {

            // Create userAchievement model ...
            UserAchievement userAchievement = new UserAchievement();

            // ... and fill with values
            userAchievement.setAchievementId(achievement.getId());
            userAchievement.setCollectorId(achievement.getCollectorId());
            userAchievement.setUserEmail(userEmail);
            userAchievement.setProgress(achievement.getProgress(userEmail)); // Call the specific progress logic

            // Collect for submission
            list.add((Model) userAchievement);
        }

        // Submit user-achievements to main application
        httpService.sendList(list, "/user-achievements");
    }
}
