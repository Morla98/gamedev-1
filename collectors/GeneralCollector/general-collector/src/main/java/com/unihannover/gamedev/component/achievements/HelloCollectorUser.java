package com.unihannover.gamedev.component.achievements;

import com.unihannover.gamedev.models.Configuration;
import com.unihannover.gamedev.models.Metric;
import org.springframework.stereotype.Component;

@Component
public class HelloCollectorUser extends AbstractAchievementComponent {

    /**
     * {@inheritDoc}
     */
    public void applyConfiguration(Configuration config) {

        this.setCollectorId(config.getCollectorId());
        this.setId("c" + config.getCollectorId() + "1");
        this.setName("Hello CollectorUser");
        this.setDescription("I am Alive");
        this.setValue(1);
    }

    /**
     * {@inheritDoc}
     */
    public float getProgress(String userEmail) {
        return (float) 100;
    }
}
