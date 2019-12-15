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

        /**
        * A controller to handle HTTP requests about acievements that belong to a user.
        */
        @RestController
        public class UserAchievementController extends BaseController {

            @Autowired
            private UserAchievementRepository repository;
            @Autowired CollectorRepository repositoryCol;

            /**
             * Returns all achievements in the repository, regardless of the user it belongs to.
             *
             * @return all UserAchievements
             */
            @RequestMapping(value="/user-achievements/all", method = RequestMethod.GET)
            public List<UserAchievement> getAllUserss() {
                return repository.findAll();
            }

            /**
             * Returns all achievements of a given user.
             *
             * @param userEmail The user to search for
             * @return All achievements in the repository that belong to the given user
             */
            @RequestMapping(value="/user-achievements/by-user-email", method = RequestMethod.GET)
            public List<UserAchievement> getUserAchievementsByUserEmail(@RequestParam(value="userEmail") String userEmail) {
                return repository.findByUserEmail(userEmail);
            }

            /**
             * Returns the preview of all achievements.
             * Previews contain the most important user achievements that have more progress than 0%.
             *
             * @param userEmail The user to generate the preview for
             * @return The list of most interesting achievements for the given user
             */
            @RequestMapping(value="/user-achievements/preview", method = RequestMethod.GET)
            public List<UserAchievement> getUserAchievementsPreview(@RequestParam(value="userEmail") String userEmail) {

                List<Collector> collectorList = repositoryCol.findAll();
                List<String> collectorIds = new ArrayList<>;

                for(Collector c : collectorList) {
                    collectorIds.add(c.getName());
                }

                CollectorPreviewDto dto = new CollectorPreviewDto(repository);
                dto.createPreviews();
                return dto.getPreviewObject();
        /*
        List <UserAchievement> preview = new ArrayList<>();

        //add non finished achievements
        for(UserAchievement u : repository.findByUserEmail(userEmail)) {
            if(u.getProgress() < 1.0 && u.getProgress() > 0.0) {
                preview.add(u);
            }
        }

        //add finished achievements
        for(UserAchievement u : repository.findByUserEmail(userEmail)) {
            if(u.getProgress() == 1.0) {
                preview.add(u);
            }
        }

        return preview;
        */
         */
    }

    /**
     * Returns the preview of all achievements in a given collector.
     * Previews contain the most important user achievements that have more progress than 0%.
     *
     * @param userEmail The user to generate the preview for
     * @param collectorId The collector to which the achievements belong
     * @return The list of most interesting achievements for the given user
     */
    @RequestMapping(value="/user-achievements/preview-for-collector", method = RequestMethod.GET)
    public List<UserAchievement> getUserAchievementsCollectorPreview(@RequestParam(value="userEmail") String userEmail, String collectorId) {

        CollectorPreviewDto dto = new CollectorPreviewDto(repository);
        return dto.getPreviewForController();
        /*
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
            if(u.getProgress() < 1.0 && u.getProgress() > 0.0) {
                preview.add(u);
            }
        }

        //add finished achievements
        for(UserAchievement u : all) {
            if(u.getProgress() == 1.0) {
                preview.add(u);
            }
        }

        return preview;
        */
         */
    }


    /**
    * Updates given UserAchievements in the repository. If there is no UserAchievement with the same ids than a given one,
    * the UserAchievements will be added to the repository.
    *
    * @param u The UserAchievement to save in the repository
    */
    @RequestMapping(value="/user-achievements", method = RequestMethod.POST)
    public void updateUserAchievements(@RequestBody UserAchievementWOT[] u) {
        for(UserAchievementWOT uWOT: u){
            repository.save(new UserAchievement(uWOT));
        }
        //repository.save(u);
    }
}