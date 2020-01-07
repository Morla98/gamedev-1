package com.unihannover.gamedev.component.achievements;

import com.unihannover.gamedev.models.Configuration;
import com.unihannover.gamedev.models.Metric;
import org.springframework.stereotype.Component;

@Component
public class Workaholic extends AbstractAchievementComponent {

    /**
     * {@inheritDoc}
     */
    public void applyConfiguration(Configuration config) {

        this.setCollectorId(config.getCollectorId());
        this.setId("c" + config.getCollectorId() + "10");
        this.setName("Workaholic");
        this.setDescription("Complete 999 issues");
        this.setValue(10);
    }

    /**
     * {@inheritDoc}
     */
    public float getProgress(String userEmail) {

        // Get ammount of distinct issues that this user has completed
        long amount = this.metricRepository.getDistinctIssueKeysByUserAndAction(userEmail, Metric.ACTION_ISSUE_DONE).size();

        return (float) Math.min(100, (float) 100 / 999 * amount);
    }
}
