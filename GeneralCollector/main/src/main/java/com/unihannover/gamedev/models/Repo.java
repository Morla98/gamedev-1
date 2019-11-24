package com.unihannover.gamedev.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Represents the repos that user has access to.
 * @author amrit
 * @version 0.0.1
 */
@Entity
public class Repo {
	@Id
	private String repoName;

	@ManyToOne
	private Achievement achievement;
	public String getRepoName() {
		return repoName;
	}
	
	
	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}
	
	
}

