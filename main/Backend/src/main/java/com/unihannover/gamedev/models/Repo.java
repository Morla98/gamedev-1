package com.unihannover.gamedev.models;

import java.util.ArrayList;

/**
 * Represents the repos that user has access to.
 * @author amrit
 * @version 0.0.1
 */
public class Repo {
	private String repoName;
	private ArrayList<Achievement> repoAchievementsList = new ArrayList<>();
	
	//getter and setter 
	public String getRepoName() {
		return repoName;
	}
	
	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}
	
	public ArrayList<Achievement> getRepoAchievementsList() {
		return repoAchievementsList;
	}

	public void setRepoAchievementsList(ArrayList<Achievement> repoAchievementsList) {
		this.repoAchievementsList = repoAchievementsList;
	}

	
	
	
}

