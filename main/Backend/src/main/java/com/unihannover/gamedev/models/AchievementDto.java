package com.unihannover.gamedev.models;

import java.sql.Timestamp;

public class AchievementDto {

	private String collectorId;

	private String name;

	private String description;

	private String userEmail;

	private float progress;
	
	private Timestamp lastUpdated;

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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public float getProgress() {
		return progress;
	}

	public void setProgress(float progress) {
		this.progress = progress;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getCollectorId() {
		return collectorId;
	}

	public void setCollectorId(String collectorId) {
		this.collectorId = collectorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	private float value;

}
