package com.unihannover.gamedev.component.achievements;

import com.unihannover.gamedev.models.Configuration;
import com.unihannover.gamedev.models.Metric;
import org.springframework.stereotype.Component;

@Component
public class NotMyJob extends AbstractAchievementComponent {

    /**
     * {@inheritDoc}
     */
    public void applyConfiguration(Configuration config) {

        this.setCollectorId(config.getCollectorId());
        this.setId("c" + config.getCollectorId() + "3");
        this.setName("Not my job!");
        this.setDescription("Assign 10 different issues from yourself to someone else");
        this.setValue(10);
    }

    /**
     * {@inheritDoc}
     */
    public float getProgress(String userEmail) {

        // Get ammount of distinct issues that this user has assigned to someone else
        long amount = this.metricRepository.getDistinctIssueKeysByUserAndAction(userEmail, Metric.ACTION_ISSUE_ASSIGNED_AWAY).size();

        return (float) Math.min(100, amount * 10);
    }
}
