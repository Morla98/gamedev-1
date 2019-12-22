package com.unihannover.gamedev.component.achievements;

import com.unihannover.gamedev.models.Configuration;
import com.unihannover.gamedev.models.Metric;
import org.springframework.stereotype.Component;

@Component
public class DivideEtImpera extends AbstractAchievementComponent {

    /**
     * {@inheritDoc}
     */
    public void applyConfiguration(Configuration config) {

        this.setCollectorId(config.getCollectorId());
        this.setId("c" + config.getCollectorId() + "9");
        this.setName("Divide et Impera");
        this.setDescription("Create 10 sub-tasks");
        this.setValue(10);
    }

    /**
     * {@inheritDoc}
     */
    public float getProgress(String userEmail) {

        // Get ammount of distinct issues that this user has created and that are Stories
        long amount = this.metricRepository.getDistinctIssueKeysByUserEmailAndEventTypeAndIssueType(userEmail, Metric.EVENT_ISSUE_CREATED, Metric.ISSUE_TYPE_SUBTASK).size();

        return (float) Math.min(100, amount * 10);
    }
}
