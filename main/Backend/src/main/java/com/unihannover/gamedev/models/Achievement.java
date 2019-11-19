package com.unihannover.gamedev.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
@Entity
public class Achievement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
	private String description;
	private int completion;
	private int value;
	private int collectorId;
	
	@Enumerated(EnumType.STRING)
	private AchievementType type; // daily, weekly, one-time
	
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
	public int getCollectorId() {
		return collectorId;
	}
	public void setCollectorId(int collectorId) {
		this.collectorId = collectorId;
	}
	public AchievementType getAchievementType(){
		return type;
	}
	public void setAchievementType(AchievementType type){
		this.type = type;
	}
	
}
