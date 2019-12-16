package com.unihannover.gamedev.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

/**
 * JUnit-Tests for package models.
 * Checks, if the methods in Achievement work correctly.
 */
class AchievementTest {
	/**
	 * Tests if the Achievement id (primary key) is returned correctly.
	 */
	@Test
	void AchievementPkTest(){
		Achievement.AchievementPK achievementPK = new Achievement.AchievementPK("ThisIsMyId", "ThisIsMyCollectorId");
		assertEquals("ThisIsMyId", achievementPK.id);
		assertEquals("ThisIsMyCollectorId", achievementPK.collectorId);
	}

	/**
	 * Tests if the Achievement id (primary key) is set correctly.
	 */
	@Test
	void setIdTest(){
		Achievement achievement = new Achievement();
		achievement.setId("ThisIsMyId");
		assertEquals("ThisIsMyId", achievement.getId());
	}

	/**
	 * Tests if the Achievement name is set correctly.
	 */
	@Test
	void SetNameTest() {
		Achievement achievement = new Achievement();
		achievement.setName("Test1");
		assertEquals("Test1", achievement.getName());
	}

	/**
	 * Tests if the Achievement name is set correctly, if set twice in a row.
	 */
	@Test
	void SetNameTwiceTest() {
		Achievement achievement = new Achievement();
		achievement.setName("Test0");
		assertEquals("Test0", achievement.getName());
		achievement.setName("Test1");
		assertEquals("Test1", achievement.getName());
	}

	/**
	 * Tests if the Achievement description is set correctly.
	 */
	@Test
	void SetDescriptionTest() {
		Achievement achievement = new Achievement();
		achievement.setDescription("Test1");
		assertEquals("Test1", achievement.getDescription());
	}

	/**
	 * Tests if the Achievement description is set correctly, if set twice in a row.
	 */
	@Test
	void SetDescriptionTwiceTest() {
		Achievement achievement = new Achievement();
		achievement.setDescription("Test0");
		achievement.setDescription("Test1");
		assertEquals("Test1", achievement.getDescription());
	}

	/**
	 * Tests if the Achievement calue is set correctly.
	 */
	@Test
	void SetValueTest(){
		Achievement achievement = new Achievement();
		achievement.setValue(300);
		assertEquals(300, achievement.getValue());
	}

	/**
	 * Tests if the Collector id of the Achievement is set correctly.
	 */
	@Test
	void setCollectorIDTest(){
		Achievement achievement = new Achievement();
		achievement.setCollectorId("ThisIsMyCollectorId");
		assertEquals("ThisIsMyCollectorId", achievement.getCollectorId());
	}
}
