package com.unihannover.gamedev.restcontroller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.unihannover.gamedev.CollectorConfig;
import com.unihannover.gamedev.CollectorConfigParser;
import com.unihannover.gamedev.models.Achievement;
import com.unihannover.gamedev.models.Metric;
import com.unihannover.gamedev.models.Model;
import com.unihannover.gamedev.models.UserAchievement;
import com.unihannover.gamedev.repositories.MetricRepository;
import com.unihannover.gamedev.services.AchievementGenerator;
import com.unihannover.gamedev.services.CollectorService;

import com.unihannover.gamedev.services.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CollectorController {


    @Autowired
    MetricRepository repository;
    @Autowired
    CollectorService service;
    @Autowired
    HttpService httpService;
    @Autowired
    AchievementGenerator achievementGenerator;


    CollectorConfig config = CollectorConfigParser.configJsonToObject();

    /**
     * for User check each achievement with MetricDB and send updated values to main application
     */
    private void updateAchievements(String useremail){
        List<Model> uaList = new ArrayList<>();
        UserAchievement newUserAchievement;

        for(Achievement achievement: achievementGenerator.getAchievementList()){
                newUserAchievement = new UserAchievement();
                newUserAchievement.setCollectorId(config.getCollectorId());
                newUserAchievement.setUserEmail(useremail);
                newUserAchievement.setAchievementId(achievement.getId());
                newUserAchievement.setProgress(achievement.getLogic().complete(useremail));
                newUserAchievement.setLastUpdated(new Timestamp(System.currentTimeMillis()));
                uaList.add(newUserAchievement);
        }
        httpService.sendList(uaList, "http://devgame:8080/api/user-achievements");

    }
    // URL IS: http://JiraCollector:8080/update
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public void update(@RequestBody String data) {
        // Debug
        Timestamp t = new Timestamp(System.currentTimeMillis());
        // System.out.println("Hook confirmed: " + t.getHours()+":"+t.getMinutes()+":"+t.getSeconds());
        Gson gson = new GsonBuilder().create();
        JsonObject job = gson.fromJson(data, JsonObject.class);
        String event = job.getAsJsonPrimitive("webhookEvent").getAsString(); // webhookEvent
        String useremail = job.getAsJsonObject("user").getAsJsonPrimitive("emailAddress").getAsString(); // emailAddress
        List<Metric> oldMetricList = repository.findByUseremail(useremail);
        System.out.println("user: " + useremail + "triggered event: "+ event);

        // if user comes for the first time, initialize his record
        if(oldMetricList.size() < 1){
            Metric initMetric = new Metric();
            initMetric.setUseremail(useremail);
            initMetric.setIssue_created(0);
            initMetric.setIssue_updated(0);
            repository.save(initMetric);
        }
        Metric oldMetric = repository.findByUseremail(useremail).get(0);

        if(event.equals("jira:issue_created")){
            Metric newMetric = new Metric(oldMetric);
            newMetric.setIssue_created(newMetric.getIssue_created()+1);
            repository.save(newMetric);
        }

        if(event.equals("jira:issue_updated")){
            Metric newMetric = new Metric(oldMetric);
            newMetric.setIssue_updated(newMetric.getIssue_updated()+1);
            repository.save(newMetric);
        }

        updateAchievements(useremail);

    }

}
