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
import com.unihannover.gamedev.services.CollectorService;

import com.unihannover.gamedev.services.GitService;
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

    private static GitService git;
    @Autowired
    MetricRepository repository;
    @Autowired
    CollectorService service;
    @Autowired
    HttpService httpService;


    CollectorConfig config = CollectorConfigParser.configJsonToObject();

    /**
     * for User check each achievement with MetricDB and send updated values to main application
     */
    private void updateAchievements(String useremail){
        List<Model> uaList = new ArrayList<>();
        UserAchievement newUserAchievement;

        for(Achievement achievement: service.getAchievementList()){
                newUserAchievement = new UserAchievement();
                newUserAchievement.setCollectorId(config.getCollectorId());
                newUserAchievement.setUserEmail(useremail);
                newUserAchievement.setAchievementId(achievement.getId());
                newUserAchievement.setProgress(achievement.getLogic().complied(useremail));
                newUserAchievement.setLastUpdated(new Timestamp(System.currentTimeMillis()));
                uaList.add(newUserAchievement);
        }
        httpService.sendList(uaList, "http://devgame:8080/api/user-achievements");

    }

    public static void setGit(GitService git){ CollectorController.git = git; }

    // URL IS: http://GitCollector:8080/update
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public void update(@RequestBody String data) {
        git.gitPull();
    }

}
