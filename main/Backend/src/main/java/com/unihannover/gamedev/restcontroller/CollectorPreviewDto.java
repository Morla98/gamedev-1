package com.unihannover.gamedev.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unihannover.gamedev.models.Collector;
import com.unihannover.gamedev.models.CollectorWOT;
import com.unihannover.gamedev.repositories.CollectorRepository;
import com.unihannover.gamedev.security.JwtTokenProvider;

/**
 * A data transfer object that
 */
public class CollectorPreviewDto{

    private UserAchievementRepository repository;
    private List<List<UserAchievement>> previewObject;

    public List<List<Userachievement>> preview;

    public CollectorPreviewDto(UserAchievementRepository repository){
        this.repository = repository;
    }

    /**
     * Returns a list of UserAchievement completed or started by a given user, regarding 1 colletor.
     *
     * @param userEmail The user that owns the Userachievements
     * @param collectorId The collector to search for
     * @return The list of UserAchievements
     */
    public List<UserAchievement> getPreviewForController(String userEmail, String collectorId) {
        List <UserAchievement> preview = new ArrayList<>();

        //add non finished achievements
        for(UserAchievement u : repository.findByUserEmail(userEmail)) {
            if(u.getProgress() < 1.0 && u.getProgress() > 0.0) {
                if (collectorId.equals(u.getCollectorId())) {
                    preview.add(u);
                }
            }
        }

        //add finished achievements
        for(UserAchievement u : repository.findByUserEmail(userEmail)) {
            if(u.getProgress() == 1.0) {
                if (collectorId.equals(u.getCollectorId())) {
                    preview.add(u);
                }
            }
        }

        return preview;
    }

    /**
     * Saves a list of all UserAchievements, sorted in individual lists for each collector in preview.
     *
     * @param userEmail The user that owns the Userachievements
     * @param collectorId The collector to search for
     */
<<<<<<< HEAD:main/Backend/src/main/java/com/unihannover/gamedev/restcontroller/CollectorPreviewDto
    public List<List<UserAchievement>> getAllPreviews(String userEmail, List<String> collectorIds) {
=======
    public List<List<UserAchievement>> generateAllPreviews(String userEmail, String[] collectorIds) {
>>>>>>> 4c7c2df540c92654f93e4f2887aa87665d1fc24e:main/Backend/src/main/java/com/unihannover/gamedev/restcontroller/CollectorPreviewDto.java

        List<List<UserAchievement>> collection = new ArrayList<>;

        for(String s : collectorIds) {
            List<UserAchievement> l = getPreviewForController(userEmail, s);
            collection.add(l);
        }

        this.preview = collection;

    }

    /**
<<<<<<< HEAD:main/Backend/src/main/java/com/unihannover/gamedev/restcontroller/CollectorPreviewDto
     * Creates a list of collector previews and saves it in an intern list.
     */
    public void createPreviews(){
        this.previewObject = getAllPreviews();
    }

    public List<List<UserAchievement>> getPreviewObject() {
        return previewObject;
=======
     * Returns a list of all UserAchievements, sorted in individual lists for each collector.
     *
     * @param userEmail The user that owns the Userachievements
     * @param collectorId The collector to search for
     * @return
     */
    public List<List<UserAchievement>> getAllPreviews(String userEmail, String[] collectorIds) {
        generateAllPreviews(userEmail, collectorIds;
        return this.preview;
>>>>>>> 4c7c2df540c92654f93e4f2887aa87665d1fc24e:main/Backend/src/main/java/com/unihannover/gamedev/restcontroller/CollectorPreviewDto.java
    }
}