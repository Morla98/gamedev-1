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

@RestController
public class AchievementController extends BaseController {

	@Autowired
    private AchievementRepository repository;

	@RequestMapping(value="/achievements/all", method = RequestMethod.GET)
	public List<Achievement> getAllAchievements() {
		return repository.findAll();
	}

	@RequestMapping(value="/achievements/by-collector-id", method = RequestMethod.GET)
    public List<Achievement> getAchievementByCollectorId(@RequestParam(value="collectorId") String id) {
        return repository.findByCollectorId(id);
    }
	
	@RequestMapping(value="/achievements", method = RequestMethod.POST)
	public void addAchievement(@RequestBody Achievement achievement) {

		repository.save(achievement);
		System.out.println(achievement.toString()); // UNDONE: Debug print
	}

	@RequestMapping(value="/achievements", method = RequestMethod.POST)
	public void addAchievementList(@RequestBody Achievement[] achievements) {

		for(Achievement a : achievements) {
			repository.save(a);
			System.out.println(a.toString()); // UNDONE: Debug print
		}

	}
}