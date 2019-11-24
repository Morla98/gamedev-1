package com.unihannover.gamedev.models;

import javax.persistence.*;

@Entity
@Table(name = "achievements_collector", schema="collector")
public class Achievement {
	
	@Id
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "completion")
	private int completion;
	@Column(name = "value")
	private int value;
	@Column(name = "collectorId")
	private long collectorId;
	@Column(name = "achievementStatus")
	private boolean achievementStatus;

	public Achievement(long id, String name, String description, int completion, int value, long collectorId){
		this.id = id;
		this.name = name;
		this.description = description;
		this.completion = completion;
		this.value = value;
		this.collectorId = collectorId;
		this.achievementStatus = false;
	}
	public boolean isAchievementStatus() {
		return achievementStatus;
	}
	public void setAchievementStatus(boolean achievementStatus) {
		this.achievementStatus = achievementStatus;
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
	public int getCompletion() {
		return completion;
	}
	public void setCompletion(int completion) {
		this.completion = completion;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public long getCollectorId() {
		return collectorId;
	}
	public void setCollectorId(long collectorId) {
		this.collectorId = collectorId;
	}
	
}
