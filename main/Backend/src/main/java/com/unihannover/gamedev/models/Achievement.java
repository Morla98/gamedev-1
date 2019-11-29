package com.unihannover.gamedev.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Achievement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String description;
	private int value;
	private int collectorId;
	
	
	//getters and setters
	public String getName() {
		return name;
	}
	public long getId() {
		return id;
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
	
}
