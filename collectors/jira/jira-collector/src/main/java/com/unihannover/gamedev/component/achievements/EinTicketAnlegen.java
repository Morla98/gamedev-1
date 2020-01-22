package com.unihannover.gamedev.component.achievements;

import com.unihannover.gamedev.models.Configuration;
import com.unihannover.gamedev.models.Metric;
import org.springframework.stereotype.Component;

@Component
public class EinTicketAnlegen extends AbstractAchievementComponent {

    /**
     * {@inheritDoc}
     */
    public void applyConfiguration(Configuration config) {

        this.setCollectorId(config.getCollectorId());
        this.setId("c" + config.getCollectorId() + "11");
        this.setName("Ein Ticket anlegen");
        this.setDescription("Lege dein erstes Jira-Ticket an. Abnahme-Testfall");
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
