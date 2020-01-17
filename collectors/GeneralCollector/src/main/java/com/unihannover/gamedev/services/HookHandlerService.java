package com.unihannover.gamedev.services;

import com.google.gson.*;
import com.unihannover.gamedev.models.Configuration;
import com.unihannover.gamedev.models.Metric;
import com.unihannover.gamedev.repositories.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HookHandlerService {

    @Autowired
    private MetricRepository metricRepository;

    @Autowired
    private UserAchievementUpdaterService userAchievementUpdaterService;

    @Autowired
    private ConfigurationService configurationService;

    /**
     * Processes a webhook object
     */
    public void handle(String data) {
        // evaluate your hook heare and insert your metric data in the metric database
		// also you may call userAchievementUpdaterService.updateForUser(userEmail, false); 
		// for upgrading the Achievements of the User this hook is from
        insertMetric(data);
        userAchievementUpdaterService.updateForUser(data, false);
    }

   

    

   

    /**
     * Insert a new metric entry into the metrics repository/database
     */
    private void insertMetric(String userEmail) {

        // Create new model
        Metric metric = new Metric(userEmail);

        // Persist model in repository
        this.metricRepository.save(metric);

        // TODO: Remove debug output
        System.out.println("Persisted new metric entry.");
    }
}
