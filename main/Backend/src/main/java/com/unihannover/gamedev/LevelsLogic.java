package com.unihannover.gamedev;

import java.util.ArrayList;
import java.util.List;

import com.unihannover.gamedev.models.Achievement;
import com.unihannover.gamedev.models.User;
import com.unihannover.gamedev.models.UserAchievement;
import com.unihannover.gamedev.repositories.AchievementRepository;
import com.unihannover.gamedev.repositories.UserAchievementRepository;
import com.unihannover.gamedev.repositories.UserRepository;

public class LevelsLogic {
	
	private final int LEVEL_LOW = 5;
	private final int LEVEL_HIGH = 20;
	private final float VALUE_LOW = 5.0f;
	private final float VALUE_HIGH = 20.0f;
	private UserRepository userRepository;
	private UserAchievementRepository userAchievementRepo;
	private AchievementRepository achievementRepository;
	private boolean firstUpdate = true;
	private List<UserAchievement> completedAchievementsList = new ArrayList<UserAchievement>();

	/**
	 * @param firstUpdate the firstUpdate to set
	 */
	public void setFirstUpdate(boolean firstUpdate) {
		this.firstUpdate = firstUpdate;
	}
	

	/**
	 * @return the completedAchievementsList
	 */
	public List<UserAchievement> getCompletedAchievementsList() {
		return completedAchievementsList;
	}


	/**
	 * @param completedAchievementsList the completedAchievementsList to set
	 */
	public void setCompletedAchievementsList(List<UserAchievement> completedAchievementsList) {
		this.completedAchievementsList = completedAchievementsList;
	}
	
	//helper function to find out if the user has lower level than the "LEVEL_LOW" value
	public boolean isLow(float level) {
		return (level <= LEVEL_LOW) ? true : false;
	}
	
	//helper function to find out if the user has medium level
	public boolean isMedium(float level) {
			return ((level > LEVEL_LOW) ? true : false)  && ((level < LEVEL_HIGH) ? true : false);
	}
		
	//helper function to find out if the user has higher level than the "LEVEL_HIGH" value
	public boolean isHigh(float level) {
			return (level >= LEVEL_HIGH) ? true : false;
	}

	//helper function to get all the completed userAchievementlist
	public List<UserAchievement> completedUserAchievementListHelper(List<UserAchievement> list){
		List<UserAchievement> resultList = new ArrayList<>();
		for(UserAchievement ua: list) {
			if(ua.getProgress() == 1.0) {
				resultList.add(ua);
			}
		}
		return resultList;
	}
	
	public void updateUserLevel(String userEmail) {
		List<UserAchievement> userAchievementList = userAchievementRepo.findByUserEmail(userEmail);
		float value = 0;
		if(firstUpdate){
			List<UserAchievement> tempList = new ArrayList<>();
			//get all the user achievements that have been completed 
			tempList = completedUserAchievementListHelper(userAchievementList);
			for(UserAchievement uAchievement : tempList) {
				//find the achievement value 
				Achievement currentAchievement = achievementRepository.findById(uAchievement.getAchievementId());
				value = currentAchievement.getValue();
				// categorize the achievement with the above range of points
				// if low, update level by 1
				// if medium update level by 2					
		     	// if high update level by 3,
				User curUser = userRepository.findByEmail(userEmail);
				if((value < VALUE_LOW) && isLow(curUser.getLevel())) {
					curUser.setLevel(curUser.getLevel() + 1);
					curUser.setScore(curUser.getScore() + VALUE_LOW * 5);
				}else if((value < VALUE_HIGH) && isLow(curUser.getLevel())) {
					curUser.setLevel(curUser.getLevel() + 3);
					curUser.setScore(curUser.getScore() + VALUE_HIGH * 5);
				}else {
					curUser.setLevel(curUser.getLevel() + 2);
					curUser.setScore(curUser.getScore() + (VALUE_LOW + VALUE_HIGH)/2 * 5);
				}
				
				tempList.add(uAchievement);
				
			}
			setCompletedAchievementsList(tempList);
			setFirstUpdate(false);
		}else {
			int prev_length = completedAchievementsList.size();
			List<UserAchievement> currentList = completedUserAchievementListHelper(userAchievementList);
			for(int i = prev_length; i < currentList.size(); i++) {
				UserAchievement curUserAchievement = currentList.get(i);
				Achievement currentAchievement = achievementRepository.findById(curUserAchievement.getAchievementId());
				value = currentAchievement.getValue();
				User curUser = userRepository.findByEmail(userEmail);
				float level = curUser.getLevel();
				if(isLow(level) && (value <= VALUE_LOW)){
					curUser.setLevel(curUser.getLevel() + 1);
				}else if(isLow(level) && (value >= VALUE_HIGH)) {
					curUser.setLevel(curUser.getLevel() + 3);
				}else if(isLow(level) && ((value > VALUE_LOW) && (value < VALUE_HIGH))) {
					curUser.setLevel(curUser.getLevel() + 2);
				}
				
				//need refinement 
				// TODO extend the logic
				/*
				if(isMedium(level) && (value <= VALUE_LOW)){
					curUser.setLevel(curUser.getLevel() + 1);
				}else if(isMedium(level) && (value >= VALUE_HIGH)) {
					curUser.setLevel(curUser.getLevel() + 3);
				}else if(isMedium(level) && ((value > VALUE_LOW) && (value < VALUE_HIGH))) {
					curUser.setLevel(curUser.getLevel() + 2);
				}
				
				if(isHigh(level) && (value <= VALUE_LOW)){
					curUser.setLevel(curUser.getLevel() + 1);
				}else if(isHigh(level) && (value >= VALUE_HIGH)) {
					curUser.setLevel(curUser.getLevel() + 3);
				}else if(isHigh(level) && ((value > VALUE_LOW) && (value < VALUE_HIGH))) {
					curUser.setLevel(curUser.getLevel() + 2);
				}*/
			
			}
		
		}

	}


}
