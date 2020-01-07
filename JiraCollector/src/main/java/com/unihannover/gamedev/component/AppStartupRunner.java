package com.unihannover.gamedev.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unihannover.gamedev.component.achievements.AbstractAchievementComponent;
import com.unihannover.gamedev.models.Collector;
import com.unihannover.gamedev.models.Configuration;
import com.unihannover.gamedev.models.Model;
import com.unihannover.gamedev.security.JwtTokenProvider;
import com.unihannover.gamedev.services.AchievementRegistry;
import com.unihannover.gamedev.services.ConfigurationService;
import com.unihannover.gamedev.services.HttpService;
import com.unihannover.gamedev.services.UserAchievementUpdaterService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    HttpService httpService;

    @Autowired
    ConfigurationService configurationService;

    @Autowired
    AchievementRegistry achievementRegistry;

    @Autowired
    UserAchievementUpdaterService userAchievementUpdaterService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Beginning initialization.");

        this.initializeCollector();
    }

    private void initializeCollector() {
        Configuration config = configurationService.getConfig();

        // Create model of myself...
        Collector me = new Collector();

        me.setName(config.getName());
        me.setId(config.getCollectorId());
        me.setToken(config.getToken());
        me.setLastSeen(new Timestamp(System.currentTimeMillis()));

        // ... and send to main application
        CloseableHttpResponse response = httpService.sendSingleModel(me, "/collectors?secret=" + config.getJwtSecret());
        if (response == null) {
            throw new RuntimeException("Error connecting to the main application API endpoint. Make sure the URL and/or the secret are correct.");
        }

        HttpEntity result = response.getEntity();
        ObjectMapper mapper = new ObjectMapper();
        Collector c = null;
        if (result != null) {
            try {
                // getting the JsonString
                String responseObject = EntityUtils.toString(result);
                // Parsing the JsonString
                c = mapper.readValue(responseObject, Collector.class);
            } catch (org.apache.http.ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (c != null) {
                if (c.getToken() != null && tokenProvider.validateToken(c.getToken())) {
                    config.setToken(c.getToken());
                }
                if (c.getId() != null) {
                    config.setCollectorId(c.getId());
                }
                configurationService.setConfig(config);
                System.out.println("Initialized Collector");
            }
        }

        int status = response.getStatusLine().getStatusCode();
        if (status == HttpStatus.SC_OK) {
            // this.initAchievements(false);
        } else if (status == HttpStatus.SC_ACCEPTED) {
            // this.initAchievements(true);
        }

        // TODO: Run this in every case ?!
        this.initializeAchievements();

        // Update userAchievements for every known user
        System.out.println("Updating user achievements for every user.");
        this.userAchievementUpdaterService.updateForAllUsers();
    }

    /**
     * Sends the list of supported achievements to the main application
     */
    private void initializeAchievements() {
        Configuration config = configurationService.getConfig();

        List<AbstractAchievementComponent> achievements = achievementRegistry.getAchievementList();

        // Remake list to list of models
        List<Model> achievementModelList = new ArrayList<>();
        for (AbstractAchievementComponent achievement : achievements) {
            achievementModelList.add((Model) achievement);
        }

        httpService.sendList(achievementModelList, "/achievements");

        System.out.println("Initialized achievements.");
    }
}
