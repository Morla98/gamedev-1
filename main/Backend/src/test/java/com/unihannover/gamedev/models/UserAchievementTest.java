package com.unihannover.gamedev.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.sql.Timestamp;

/**
 * JUnit-Tests for package models.
 * Checks, if the methods in UserAchievement work correctly.
 */
class UserAchievementTest {

	/**
	 * Tests if the UserAchievement E-Mail adress (primary key of User) is returned correctly.
	 */
	@Test
	void setUserEmailTest() {
		UserAchievement rel = new UserAchievement();
		rel.setUserEmail("abcdef123@adesso.de");
		assertEquals("abcdef123@adesso.de",rel.getUserEmail());
	}

	/**
	 * Tests if the UserAchievement achievement id (primary key of Achievement) is set correctly.
	 */
	@Test
	void setAchievementIdTest() {
		UserAchievement rel = new UserAchievement();
		rel.setAchievementId("123IamId");
		assertEquals("123IamId",rel.getAchievementId());
	}

	/**
	 * Tests if the UserAchievement collector id (primary key of Collector) is set correctly.
	 */
	@Test
	void setCollectorIdTest(){
		UserAchievement rel = new UserAchievement();
		rel.setCollectorId("123IamId");
		assertEquals("123IamId", rel.getCollectorId());
	}

	/**
	 * Tests if the UserAchievement progress is set correctly.
	 */
	@Test
	void setProgress() {
		UserAchievement rel = new UserAchievement();
		rel.setProgress(50);
		assertEquals(50, rel.getProgress());
	}

	/**
	 * Tests if the UserAchievement timestamp is set correctly.
	 */
	@Test
	void setLastUpdatedTest() {
		UserAchievement rel = new UserAchievement();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		rel.setLastUpdated(timestamp);
		assertEquals(timestamp,rel.getLastUpdated());
	}

	/**
	 * Tests if the all primary keys are set correctly.
	 */
	@Test
	void UserAchievementPKTest(){
		UserAchievement.UserAchievementPK  uapk= new UserAchievement.UserAchievementPK("MyAchievementId", "MyCollectorId", "MyUserEmail");
		assertEquals(uapk.achievementId, "MyAchievementId");
		assertEquals(uapk.collectorId, "MyCollectorId");
		assertEquals(uapk.userEmail, "MyUserEmail");

	}
}
