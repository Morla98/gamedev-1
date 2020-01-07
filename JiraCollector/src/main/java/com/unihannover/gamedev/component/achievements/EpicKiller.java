package com.unihannover.gamedev.component.achievements;

import com.unihannover.gamedev.models.Configuration;
import com.unihannover.gamedev.models.Metric;
import org.springframework.stereotype.Component;

@Component
public class EpicKiller extends AbstractAchievementComponent {

    /**
     * {@inheritDoc}
     */
    public void applyConfiguration(Configuration config) {

        this.setCollectorId(config.getCollectorId());
        this.setId("c" + config.getCollectorId() + "7");
        this.setName("Epic Killer");
        this.setDescription("Complete 10 Epics");
        this.setValue(10);
    }

    /**
     * {@inheritDoc}
     */
    public float getProgress(String userEmail) {

        // Get ammount of distinct issues that this user has completed and who are an Epic
        long amount = this.metricRepository.getDistinctIssueKeysByUserAndActionAndIssueType(userEmail, Metric.ACTION_ISSUE_DONE, Metric.ISSUE_TYPE_EPIC).size();

        return (float) Math.min(100, amount * 10);
    }
}
