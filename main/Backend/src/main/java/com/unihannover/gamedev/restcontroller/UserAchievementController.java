package com.unihannover.gamedev.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unihannover.gamedev.models.*;
import com.unihannover.gamedev.repositories.*;
import com.unihannover.gamedev.security.*;


/**
 * A controller to handle HTTP requests about acievements that belong to a user.
 */
@RestController
public class UserAchievementController extends BaseController {

	@Autowired
	private UserAchievementRepository userAchievementRepo;

    @Autowired
    private CollectorRepository collectorRepo;

    @Autowired
    private AchievementRepository achievementRepo;

    /**
     * Returns all achievements in the repository, regardless of the user it belongs to.
     *
     * @return all UserAchievements
     */
    @RequestMapping(value="/user-achievements/all", method = RequestMethod.GET)
    public List<UserAchievement> getAllUsers() {
                return userAchievementRepo.findAll();
            }

    /**
     * Returns all achievements of a given user.
     *
     * @param collectorId The collector to get achievements from
     * @return All achievements in the repository that belong to the given user
     */
    @RequestMapping(value = "/user-achievements/by-collector-id", method = RequestMethod.GET)
    public List<AchievementDto> getUserAchievementsByCollectorId(@RequestParam(value = "collectorId") String collectorId) {

        String userEmail = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        List<UserAchievement> achievements = userAchievementRepo.findByUserEmail(userEmail);
        List<AchievementDto> dtos = new ArrayList<AchievementDto>();
        for (UserAchievement u : achievements) {
            //System.out.println(u.getUserEmail() + " c" + u.getCollectorId() + " a" + u.getAchievementId());
            //System.out.println("searching for collector: " + collectorId);
            if (u.getCollectorId().equals(collectorId)) {

                List<Achievement> aList = achievementRepo.findByCollectorId(collectorId);
                Achievement a = null;
                for (Achievement ach : aList) {
                    //System.out.println(ach.getDescription() + " c" + ach.getCollectorId() + " a" + ach.getId());
                    if (ach.getId().equals(u.getAchievementId())) {
                        a = ach;
                    }
                }
                if (a != null) {
                    dtos.add(new AchievementDto(u.getCollectorId(), a.getName(), a.getDescription(), userEmail,
                            u.getProgress(), null, a.getValue()));
                }
            }
        }
        return dtos;
    }


    /**
         * Returns the previews of all achievements in a List of PreviewDtos.
         * Previews contain the most important user achievements that have more progress than 0%.
         *
         * @param userEmail The user to generate the preview for
         * @return The list of most interesting achievements for the given user
         */
        @RequestMapping(value="/user-achievements/preview", method = RequestMethod.GET)
        public List<PreviewDto> getUserAchievementsPreview() {

            String userMail = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

            List<Collector> collectorList = collectorRepo.findAll();
            List<String> collectorIds = new ArrayList<>();
            List<PreviewDto> dtoList = new ArrayList<>();

            for(Collector c : collectorList) {
                String collectorId = c.getId();
                PreviewDto dto = getUserAchievementsCollectorPreview(collectorId);
                dtoList.add(dto);
            }

            return dtoList;

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
    }

    public Achievement findAchievement(String id){
        List<Achievement> achievementList = achievementRepo.findAll();
        for(Achievement a : achievementList) {
            if(a.getId().equals(id)){
                return a;
            }
        }
        return null;
    }

    /**
     * Returns the preview of all achievements in a given collector in form of a PreviewDto.
     * Previews contain the most important user achievements that have more progress than 0%.
     *
     * @param userEmail The user to generate the preview for
     * @param collectorId The collector to which the achievements belong
     * @return The list of most interesting achievements for the given user
     */
    @RequestMapping(value="/user-achievements/preview-for-collector", method = RequestMethod.GET)
    public PreviewDto getUserAchievementsCollectorPreview(@RequestParam(value="collectorId") String collectorId) {

        String userMail = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        int achievementCount = 4;

        List<UserAchievement> list = new ArrayList<>();
        //add non finished achievements
        for(UserAchievement u : userAchievementRepo.findByUserEmail(userMail)) {
            if(u.getProgress() < 1.0 && u.getProgress() > 0.0) {
                if (collectorId.equals(u.getCollectorId())) {
                    list.add(u);
                }
            }
        }
        //add finished achievements
        for(UserAchievement u : userAchievementRepo.findByUserEmail(userMail)) {
            if(u.getProgress() == 1.0) {
                if (collectorId.equals(u.getCollectorId())) {
                    list.add(u);
                }
            }
        }
        //add non-progress achievements
        for(UserAchievement u : userAchievementRepo.findByUserEmail(userMail)) {
            if(u.getProgress() == 0.0) {
                if (collectorId.equals(u.getCollectorId())) {
                    list.add(u);
                }
            }
        }

        List<PreviewDto> dtoList = new ArrayList<>();

        PreviewDto dto = new PreviewDto(collectorId);
        for(int i = 0; i < achievementCount || i < list.size(); i++){
            UserAchievement ua = list.get(i);
            Achievement a = findAchievement(ua.getAchievementId());

            AchievementDto aDto = new AchievementDto(a.getCollectorId(), a.getName(), a.getDescription(), userMail, ua.getProgress(),
            null, a.getValue());

            dto.addUserAchievement(aDto);
        }

        return dto;

        /*
        CollectorPreviewDto cDto = new CollectorPreviewDto(repository);

        List<UserAchievement> list = cDto.getPreviewForController(userEmail, collectorId);

        List<PreviewDto> dtoList = new ArrayList<>();

        PreviewDto dto = new PreviewDto(collectorId);
        for(int i = 0; i < 4 || i < list.size(); i++){
            UserAchievement ua = list.get(i);
            Achievement a = findAchievement(ua.getAchievementId());

            dto.addUserAchievement(a.getName(), a.getDescription(), ua.getProgress(), i);
        }

        return dto;
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
            userAchievementRepo.save(new UserAchievement(uWOT));
        }
        //repository.save(u);
    }
}