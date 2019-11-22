package com.unihannover.gamedev.models;

/**
 * This class represents the achievements specific to one User 
 * @author amrit
 *
 */
public class UserSpecificAchievements extends Achievement {
	
	private String userId;
	private boolean achievementStatus;
	private int completionRate;
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the achievementStatus
	 */
	public boolean isAchievementStatus() {
		return achievementStatus;
	}
	/**
	 * @param achievementStatus the achievementStatus to set
	 */
	public void setAchievementStatus(boolean achievementStatus) {
		this.achievementStatus = achievementStatus;
	}
	/**
	 * @return the completionRate
	 */
	public int getCompletionRate() {
		return completionRate;
	}
	/**
	 * @param completionRate the completionRate to set
	 */
	public void setCompletionRate(int completionRate) {
		this.completionRate = completionRate;
	}
	
	
}
