package com.unihannover.gamedev.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unihannover.gamedev.models.Achievement;
import com.unihannover.gamedev.repositories.AchievementRepository;

/**
 * A controller to handle HTTP requests about Achievements.
 *
 * @author Lukas Niehus
 */
@RestController
public class AchievementController extends BaseController {


	@Autowired
    private AchievementRepository repository;

	/**
	 * Returns all achievements in the repository.
	 *
	 * @return A list with all achievements
	 */
	@RequestMapping(value="/achievements/all", method = RequestMethod.GET)
	public List<Achievement> getAllAchievements() {
		return repository.findAll();
	}

	/**
	 * Returns all achievements, that belong to a given collector.
	 *
	 * @param id The id of the collector
	 * @return A list with all fitting achievements
	 */
	@RequestMapping(value="/achievements/by-collector-id", method = RequestMethod.GET)
    public List<Achievement> getAchievementByCollectorId(@RequestParam(value="collectorId") String id) {
        return repository.findByCollectorId(id);
    }

	/**
	 * Adds multiple achievements to the repository. If there is already an achievement with the given id,
	 * the existing achievement will be updated.
	 *
	 * @param achievements A list of achievements to add
	 */
	@RequestMapping(value="/achievements", method = RequestMethod.POST)
	public void addAchievementList(@RequestBody Achievement[] achievements) {

		for(Achievement a : achievements) {
			repository.save(a);
			System.out.println(a.toString()); // UNDONE: Debug print
		}

	}
}