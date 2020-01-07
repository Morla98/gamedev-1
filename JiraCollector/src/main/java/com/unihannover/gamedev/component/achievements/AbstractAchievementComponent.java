package com.unihannover.gamedev.component.achievements;

import com.unihannover.gamedev.models.Achievement;
import com.unihannover.gamedev.models.Configuration;
import com.unihannover.gamedev.repositories.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Abstract class for achievement components
 */
@Component
abstract public class AbstractAchievementComponent extends Achievement implements AchievementLogicInterface {

    /**
     * The repository for access to metrics of this collector
     */
    protected MetricRepository metricRepository;

    /**
     * Used to inject the metrics repository into every child class
     */
    @Autowired
    final public void setMetricRepository(MetricRepository metricRepository) {
        this.metricRepository = metricRepository;
    }

    /**
     * Setup the achievement by collector configuration
     * (some properties of the achievement may depend on the collector configuration, e.g. id and collectorId)
     */
    abstract public void applyConfiguration(Configuration config);

    /**
     * {@inheritDoc}
     */
    abstract public float getProgress(String userEmail);
}
