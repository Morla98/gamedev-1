package com.unihannover.gamedev.component.achievements;

import com.unihannover.gamedev.models.Configuration;
import com.unihannover.gamedev.models.Metric;
import org.springframework.stereotype.Component;

@Component
public class TheCleaner extends AbstractAchievementComponent {

    /**
     * {@inheritDoc}
     */
    public void applyConfiguration(Configuration config) {

        this.setCollectorId(config.getCollectorId());
        this.setId("c" + config.getCollectorId() + "4");
        this.setName("The Cleaner");
        this.setDescription("Delete 5 issues");
        this.setValue(5);
    }

    /**
     * {@inheritDoc}
     */
    public float getProgress(String userEmail) {

        // Get ammount of distinct issues that this user has deleted
        long amount = this.metricRepository.getDistinctIssueKeysByUserAndEvent(userEmail, Metric.EVENT_ISSUE_DELETED).size();

        return (float) Math.min(100, amount * 100);
    }
}
