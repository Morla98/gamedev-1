package com.unihannover.gamedev;

import java.util.List;

import com.unihannover.gamedev.models.Achievement;
import com.unihannover.gamedev.models.User;
import com.unihannover.gamedev.models.UserAchievement;
import com.unihannover.gamedev.repositories.AchievementRepository;
import com.unihannover.gamedev.repositories.UserAchievementRepository;
import com.unihannover.gamedev.repositories.UserRepository;

public class LevelsLogic {
	private final int LOW_UPDATE = 20;
	private final int MEDIUM_UPDATE = 40;
	private final int HIGH_UPDATE = 80;
	private UserRepository userRepository;
	private UserAchievementRepository userAchievementRepo;
	private AchievementRepository achievementRepository;

	public Levels findLevel(float userLevel) {
		if(userLevel < 5.0) return Levels.LOW;
		if(userLevel >= 5.0 && userLevel < 20.0) return Levels.MEDIUM;
		return Levels.HIGH;
	}
	
	
	//helper function to calculate the total points and return it 
	public int calculatePoint(List<UserAchievement> list) {
		int total = 0;
		//get the formula for calculating the points via progression
		for(UserAchievement userAchievement : list) {
			Achievement currentAchievement = achievementRepository.findById(userAchievement.getAchievementId());
			float value = currentAchievement.getValue();
			float progress = userAchievement.getProgress();
			//formula for total score = (progress in % / 100) * value of achievement  
			total += (int) ((progress / 100) * value);
		}
		return total;
	}

	/**
	 * Method to update the score of the current user based on achievements progression
	 * @param userEmail
	 */
	
	public void updateScore(String userEmail) {
		//list of achievements of user 
		List<UserAchievement> userAchievementList = userAchievementRepo.findByUserEmail(userEmail);
		User curUser = userRepository.findByEmail(userEmail);
		
		// score of user before update 
		int curScore = (int)curUser.getScore();
		// calculate the newScore 
		int newScore = calculatePoint(userAchievementList);
		
		// compare with the last score and get diff 
		int gainedScore = newScore - curScore;
		
		// find the current level of user
		int curLevel = (int)curUser.getLevel();
		//Levels States == LOW, HIGH, MEDIUM
		Levels curLevelsState = findLevel(curLevel);
		
		if(curLevelsState == Levels.LOW) {
			// update levels easily 
			//each 20 gained points increases levels by 1
			if(gainedScore >= LOW_UPDATE) {
				int update = (int)(gainedScore / LOW_UPDATE);
				curUser.setLevel(curLevel + update);
				curUser.setScore(newScore);
			}
		}else if (curLevelsState == Levels.MEDIUM) {
			// update levels moderately
			//each 40 points gained updates levels by 1
			if(gainedScore >= MEDIUM_UPDATE) {
				int update = (int)(gainedScore / MEDIUM_UPDATE);
				curUser.setLevel(curLevel + update);
				curUser.setScore(newScore);
			}
		}else {
			//update levels slowly
			// each 80 points gained updates levels by 1
			if(gainedScore >= HIGH_UPDATE) {
				int update = (int)(gainedScore / HIGH_UPDATE);
				curUser.setLevel(curLevel + update);
				curUser.setScore(newScore);
			}
		}	
	}

}
