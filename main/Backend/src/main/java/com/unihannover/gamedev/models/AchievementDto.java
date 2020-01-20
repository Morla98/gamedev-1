package com.unihannover.gamedev.models;

import java.sql.Timestamp;

public class AchievementDto {

	private String collectorId;

	private String name;

	private String description;

	private String userEmail;

	private float progress;
	
	private Timestamp lastUpdated;

	private float value;

	public AchievementDto(String collectorId, String name, String description, String userEmail, float progress,
			Timestamp lastUpdated, float value) {
		super();
		this.collectorId = collectorId;
		this.name = name;
		this.description = description;
		this.userEmail = userEmail;
		this.progress = progress;
		this.lastUpdated = lastUpdated;
		this.value = value;
	}

	/**
	 * Returns the email adress (primary key) of the User.
	 *
	 * @return The email of the user
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * Sets the email adress (primary key) of the User.
	 *
	 * @param userEmail The email of the user
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * Returns the progress of the Achievement by its user.
	 *
	 * @return The progress of the Achievement
	 */
	public float getProgress() {
		return progress;
	}

	/**
	 * Sets the progress of the Achievement by its user.
	 *
	 * @param progress The progress of the Achievement
	 */
	public void setProgress(float progress) {
		this.progress = progress;
	}

	/**
	 * Returns the last noticed timestamp of the Achievement by its user.
	 *
	 * @return The time of the last update
	 */
	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * Sets the last noticed timestamp of the Achievement by its user.
	 *
	 * @param lastUpdated The time of the last update
	 */
	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/**
	 * Returns the collector, the Achievement belongs to.
	 *
	 * @return The Collector of the Achievement
	 */
	public String getCollectorId() {
		return collectorId;
	}

	/**
	 * Sets the collector, the Achievement belongs to.
	 *
	 * @param collectorId The Collector of the Achievement
	 */
	public void setCollectorId(String collectorId) {
		this.collectorId = collectorId;
	}

	/**
	 * Returns the Name of the Achievement.
	 *
	 * @return The Name of the Achievement
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Name of the Achievement.
	 *
	 * @param name The Name of the Achievement
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the description of the Achievement.
	 * Contains information about what has to be done to gather progress on the achievment.
	 *
	 * @return The description of the Achievement
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the Achievement.
	 * Contains information about what has to be done to gather progress on the achievment.
	 *
	 * @param description The description of the Achievement
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the value of the Achievement.
	 *
	 * @return The value of the Achievement
	 */
	public float getValue() {
		return value;
	}

	/**
	 * Sets the value of the Achievement.
	 *
	 * @param calue The value of the Achievement
	 */
	public void setValue(float value) {
		this.value = value;
	}

}
