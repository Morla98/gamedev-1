package com.unihannover.gamedev.component.achievements;

import com.unihannover.gamedev.models.Configuration;
import com.unihannover.gamedev.models.Metric;
import org.springframework.stereotype.Component;

@Component
public class HelloJira extends AbstractAchievementComponent {

    /**
     * {@inheritDoc}
     */
    public void applyConfiguration(Configuration config) {

        this.setCollectorId(config.getCollectorId());
        this.setId("c" + config.getCollectorId() + "1");
        this.setName("Hello Jira");
        this.setDescription("Create your first issue");
        this.setValue(1);
    }

    /**
     * {@inheritDoc}
     */
    public float getProgress(String userEmail) {

        // Get ammount of distinct issues that this user has created
        long amount = this.metricRepository.getDistinctIssueKeysByUserAndEvent(userEmail, Metric.EVENT_ISSUE_CREATED).size();

        return (float) Math.min(100, amount * 100);
    }
}
