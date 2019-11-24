package com.unihannover.gamedev.models;

/**
 * This class represents the information whether a user has an achievement.
 * @author amrit
 *
 */
public class UserHasAchievement {
	private String userId;
	private long achievementId;
	private boolean achievementAccomplished;
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
	 * @return the achievementAccomplished
	 */
	public boolean isAchievementAccomplished() {
		return achievementAccomplished;
	}
	/**
	 * @param achievementAccomplished the achievementAccomplished to set
	 */
	public void setAchievementAccomplished(boolean achievementAccomplished) {
		this.achievementAccomplished = achievementAccomplished;
	}
	/**
	 * @return the achievementId
	 */
	public long getAchievementId() {
		return achievementId;
	}
	/**
	 * @param achievementId the achievementId to set
	 */
	public void setAchievementId(long achievementId) {
		this.achievementId = achievementId;
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
