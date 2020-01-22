package com.unihannover.gamedev.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import java.sql.Timestamp;
import java.io.Serializable;
/**
 * Represents an achievement achievable by a user
 */
@Entity
@IdClass(UserAchievement.UserAchievementPK.class)
@Table(name = "user_achievements", schema = "main")
public class UserAchievement {

	/**
	 * Required for composite primary keys
	 */
	public static class UserAchievementPK implements Serializable {
		protected String achievementId;
		protected String collectorId;
		protected String userEmail;

		public UserAchievementPK() {}

		public UserAchievementPK(String achievementId, String collectorId, String userEmail) {
			this.achievementId = achievementId;
			this.collectorId = collectorId;
			this.userEmail = userEmail;
		}

		// equals, hashCode ?!
	}

	public UserAchievement(){}
	public UserAchievement(UserAchievementWOT a) {
		this.achievementId = a.getAchievementId();
		this.collectorId = a.getCollectorId();
		this.progress = a.getProgress();
		this.userEmail = a.getUserEmail();
		this.lastUpdated = new Timestamp(System.currentTimeMillis());
	}

	@Id
	@Column(name = "achievement_id")
	private String achievementId;
	@Id
	@Column(name = "collector_id")
	private String collectorId;

	@Id
	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "progress")
	private float progress;

	@Column(name = "last_updated")
	private Timestamp lastUpdated;

	// *** Autogenerated Setters & Getters ***

	/**
	 * Returns the id of the Achievement, the UserAchievement belongs to (primary key).
	 *
	 * @return The AId
	 */
	public String getAchievementId() {
		return achievementId;
	}

	/**
	 * Sets the id of the Achievement.
	 * Note that the achievement id is a primary key.
	 *
	 * @param achievementId The AId
	 */
	public void setAchievementId(String achievementId) {
		this.achievementId = achievementId;
	}

	/**
	 * Returns the id of the Collector, the UserAchievement belongs to (primary key).
	 *
	 * @return The CId
	 */
	public String getCollectorId() {
		return collectorId;
	}

	/**
	 * Sets the id of the Collector.
	 * Note that the collector id is a primary key.
	 *
	 * @param collectorId
	 */
	public void setCollectorId(String collectorId) {
		this.collectorId = collectorId;
	}

	/**
	 * Returns the E-Mail adress of the Collector, the UserAchievement belongs to (primary key).
	 *
	 * @return The user E-Mail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * Sets the id of the Collector.
	 * Note that the user E-Mail is a primary key.
	 *
	 * @param userEmail The user E-Mail
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * Returns the progress of the UserAchievement (usually between 0 and 1).
	 * If the Progress is 0, The Achievement is not in progress.
	 * If the Progress is 1, the Achievement is completed.
	 *
	 * @return The progress of the UserAchievement
	 */
	public float getProgress() {
		return progress;
	}

	/**
	 * Sets the progress of the UserAchievement (usually between 0 and 1).
	 * If the Progress is 0, The Achievement is not in progress.
	 * If the Progress is 1, the Achievement is completed.
	 *
	 * @param progress The progress of the UserAchievement
	 */
	public void setProgress(float progress) {
		this.progress = progress;
	}

	/**
	 * Returns the latest time, the UserAchievement was updated.
	 *
	 * @return The Timestamp
	 */
	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * Sets the latest time, the UserAchievement was updated.
	 * Usually used to set it to the time this function is called.
	 *
	 * @param lastUpdated The new Timestamp
	 */
	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}
