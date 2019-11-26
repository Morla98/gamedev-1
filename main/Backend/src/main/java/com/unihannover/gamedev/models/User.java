package com.unihannover.gamedev.models;

/**
 * Represents the user 
 * @author amrit
 * @version 0.0.1
 */
public class User {
	private String firstName;
	private String lastName;
	private String email;
	private String userName;
	private int level;
	private int score;
	private boolean anonymous;
	
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public boolean isAnonymous() {
		return anonymous;
	}
	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}

	public String toString() {
		String s = "";
		if(this.anonymous) {
			s = "*";
		}
		return s += this.userName + "(" + this.lastName + ", " this.firstName + ") ";
	}
	
	
}
