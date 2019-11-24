package com.unihannover.gamedev.models;

import javax.validation.ReportAsSingleViolation;

/**
 * Represents the user 
 * @author amrit
 * @version 0.0.1
 */
public class User {
	private String fullName;
	private String email;
	private String userName;
	private int level;
	private int score;
	private Repo repo;
	private boolean anonymous;
	
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Repo getRepo() {
		return repo;
	}
	public void setRepo(Repo repo) {
		this.repo = repo;
	}
	public boolean isAnonymous() {
		return anonymous;
	}
	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}
	
	/**
	 * method to login in the system
	 */
	public void login(){
		
	}
	
	/**
	 * method to update profile
	 */
	public void updateProfile() {
		
		
	}
	
}
