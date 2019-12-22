package com.unihannover.gamedev.component.achievements;

import com.unihannover.gamedev.models.Configuration;
import com.unihannover.gamedev.models.Metric;
import org.springframework.stereotype.Component;

@Component
public class MyJob extends AbstractAchievementComponent {

    /**
     * {@inheritDoc}
     */
    public void applyConfiguration(Configuration config) {

        this.setCollectorId(config.getCollectorId());
        this.setId("c" + config.getCollectorId() + "2");
        this.setName("My job!");
        this.setDescription("Assign five different tickets to yourself");
        this.setValue(5);
    }

    /**
     * {@inheritDoc}
     */
    public float getProgress(String userEmail) {

        // Get ammount of distinct tickets that this user has assigned to someone else
        long amount = this.metricRepository.getDistinctIssueKeysByUserAndAction(userEmail, Metric.ACTION_ISSUE_ASSIGNED_TO_ME).size();

        return (float) Math.min(100, amount * 20);
    }
}
