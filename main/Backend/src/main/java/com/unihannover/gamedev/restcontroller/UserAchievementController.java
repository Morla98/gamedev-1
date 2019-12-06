package com.unihannover.gamedev.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unihannover.gamedev.models.Achievement;
import com.unihannover.gamedev.models.AchievementDto;
import com.unihannover.gamedev.models.UserAchievement;
import com.unihannover.gamedev.models.UserAchievementWOT;
import com.unihannover.gamedev.repositories.AchievementRepository;
import com.unihannover.gamedev.repositories.UserAchievementRepository;

@RestController
public class UserAchievementController extends BaseController {

	@Autowired
	private UserAchievementRepository userAchievementRepo;

	@Autowired
	private AchievementRepository achievementRepo;

	@RequestMapping(value = "/user-achievements/all", method = RequestMethod.GET)
	public List<UserAchievement> getAllUserss() {
		return userAchievementRepo.findAll();
	}

	@RequestMapping(value = "/user-achievements/by-user-email", method = RequestMethod.GET)
	public List<AchievementDto> getUserAchievementsByUserEmail(@RequestParam(value = "userEmail") String userEmail,
			@RequestParam(value = "collectorId") String collectorId) {
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

	@RequestMapping(value = "/user-achievements/preview", method = RequestMethod.GET)
	public List<UserAchievement> getUserAchievementsPreview(@RequestParam(value = "userEmail") String userEmail) {
		return userAchievementRepo.findByUserEmail(userEmail);
	}

	@RequestMapping(value = "/user-achievements/preview-for-collector", method = RequestMethod.GET)
	public List<AchievementDto> getUserAchievementsCollectorPreview(@RequestParam(value = "userEmail") String userEmail,
			@RequestParam(value = "collectorId") int collectorId) {
		/*
		 * List<UserAchievement> achievements =
		 * userAchievementRepo.findByUserEmail(userEmail); List<AchievementDto> dtos =
		 * new ArrayList<AchievementDto>(); for (UserAchievement u : achievements) {
		 * Achievement a = achievementRepo.findById(u.getAchievementId()); dtos.add(new
		 * AchievementDto(u.getCollectorId(), a.getName(), a.getDescription(),
		 * userEmail, u.getProgress(), null, a.getValue())); }
		 */
		return null;
	}

	@RequestMapping(value = "/user-achievements", method = RequestMethod.POST)
	public void updateUserAchievements(@RequestBody UserAchievementWOT u) {
		userAchievementRepo.save(new UserAchievement(u));
		// repository.save(u);
	}
}