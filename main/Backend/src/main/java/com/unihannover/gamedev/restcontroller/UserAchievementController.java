package com.unihannover.gamedev.restcontroller;

import java.util.List;
import java.util.ArrayList;

import com.unihannover.gamedev.models.UserAchievementWOT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unihannover.gamedev.models.User;
import com.unihannover.gamedev.models.UserAchievement;
import com.unihannover.gamedev.repositories.UserAchievementRepository;

        @RestController
        public class UserAchievementController extends BaseController {

            @Autowired
            private UserAchievementRepository repository;

            @RequestMapping(value="/user-achievements/all", method = RequestMethod.GET)
            public List<UserAchievement> getAllUserss() {
                return repository.findAll();
            }

            @RequestMapping(value="/user-achievements/by-user-email", method = RequestMethod.GET)
            public List<UserAchievement> getUserAchievementsByUserEmail(@RequestParam(value="userEmail") String userEmail) {
                return repository.findByUserEmail(userEmail);
            }

            @RequestMapping(value="/user-achievements/preview", method = RequestMethod.GET)
            public List<UserAchievement> getUserAchievementsPreview(@RequestParam(value="userEmail") String userEmail) {

        List <UserAchievement> preview = new ArrayList<>();

        //add non finished achievements
        for(UserAchievement u : repository.findByUserEmail(userEmail)) {
            if(u.getProgress() < 1.0 && u.getProgress() > 0.0) {
                preview.add(u);
            }
        }

        //add finished achievements
        for(UserAchievement u : repository.findByUserEmail(userEmail)) {
            if(u.getProgress() == 1) {
                preview.add(u);
            }
        }

        return preview;
    }

    @RequestMapping(value="/user-achievements/preview-for-collector", method = RequestMethod.GET)
    public List<UserAchievement> getUserAchievementsCollectorPreview(@RequestParam(value="userEmail") String userEmail, String collectorId) {

        List <UserAchievement> preview = new ArrayList<>();
        List <UserAchievement> all = new ArrayList<>();

        //Only include the UserAchievements with correct collector id
        for(UserAchievement u : repository.findByUserEmail(userEmail)) {
            if(collectorId.equals(u.getCollectorId())) {
                all.add(u);
            }
        }

        //add non finished achievements
        for(UserAchievement u : all) {
            if(u.getProgress() < 1 && u.getProgress() > 0) {
                preview.add(u);
            }
        }

        //add finished achievements
        for(UserAchievement u : all) {
            if(u.getProgress() == 1) {
                preview.add(u);
            }
        }

        return preview;
    }


    @RequestMapping(value="/user-achievements", method = RequestMethod.POST)
    public void updateUserAchievements(@RequestBody UserAchievementWOT[] u) {
        for(UserAchievementWOT uWOT: u){
            repository.save(new UserAchievement(uWOT));
        }
        //repository.save(u);
    }
}