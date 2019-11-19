package com.unihannover.gamedev.models;

import java.util.ArrayList;

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
	private boolean anonymous;
	private Repo repo;
	private Achievement achievement;
	private ArrayList<Repo> repoList = new ArrayList<>();
	
	
	//getters and setters
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
	
	public ArrayList<Repo> getRepoList() {
		return repoList;
	}
	public void setRepoList(ArrayList<Repo> repoList) {
		this.repoList = repoList;
	}
	public Achievement getAchievement() {
		return achievement;
	}
	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}
	
	
	
}
