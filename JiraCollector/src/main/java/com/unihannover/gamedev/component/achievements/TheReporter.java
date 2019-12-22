package com.unihannover.gamedev.component.achievements;

import com.unihannover.gamedev.models.Configuration;
import com.unihannover.gamedev.models.Metric;
import org.springframework.stereotype.Component;

@Component
public class TheReporter extends AbstractAchievementComponent {

    /**
     * {@inheritDoc}
     */
    public void applyConfiguration(Configuration config) {

        this.setCollectorId(config.getCollectorId());
        this.setId("c" + config.getCollectorId() + "5");
        this.setName("The Reporter");
        this.setDescription("Create 50 tickets");
        this.setValue(50);
    }

    /**
     * {@inheritDoc}
     */
    public float getProgress(String userEmail) {

        // Get ammount of distinct tickets that this user has created
        long amount = this.metricRepository.getDistinctIssueKeysByUserAndEvent(userEmail, Metric.EVENT_ISSUE_CREATED).size();

        return (float) Math.min(100, amount * 2);
    }
}
