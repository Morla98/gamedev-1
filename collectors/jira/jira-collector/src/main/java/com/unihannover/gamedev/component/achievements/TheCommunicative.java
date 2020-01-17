package com.unihannover.gamedev.component.achievements;

import com.unihannover.gamedev.models.Configuration;
import com.unihannover.gamedev.models.Metric;
import org.springframework.stereotype.Component;

@Component
public class TheCommunicative extends AbstractAchievementComponent {

    /**
     * {@inheritDoc}
     */
    public void applyConfiguration(Configuration config) {

        this.setCollectorId(config.getCollectorId());
        this.setId("c" + config.getCollectorId() + "6");
        this.setName("The Communicative");
        this.setDescription("Comment on 50 different issues");
        this.setValue(50);
    }

    /**
     * {@inheritDoc}
     */
    public float getProgress(String userEmail) {

        // Get ammount of distinct issues that this user has commented on
        long amount = this.metricRepository.getDistinctIssueKeysByUserAndAction(userEmail, Metric.ACTION_ISSUE_COMMENTED).size();

        return (float) Math.min(100, amount * 2);
    }
}
