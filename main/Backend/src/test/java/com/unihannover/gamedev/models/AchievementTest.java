package com.unihannover.gamedev.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

class AchievementTest {
	@Test
	void AchievementPkTest(){
		Achievement.AchievementPK achievementPK = new Achievement.AchievementPK("ThisIsMyId", "ThisIsMyCollectorId");
		assertEquals("ThisIsMyId", achievementPK.id);
		assertEquals("ThisIsMyCollectorId", achievementPK.collectorId);
	}
	@Test
	void setIdTest(){
		Achievement achievement = new Achievement();
		achievement.setId("ThisIsMyId");
		assertEquals("ThisIsMyId", achievement.getId());
	}
	@Test
	void SetNameTest() {
		Achievement achievement = new Achievement();
		achievement.setName("Test1");
		assertEquals("Test1", achievement.getName());
	}
	@Test
	void SetNameTwiceTest() {
		Achievement achievement = new Achievement();
		achievement.setName("Test0");
		assertEquals("Test0", achievement.getName());
		achievement.setName("Test1");
		assertEquals("Test1", achievement.getName());
	}
	@Test
	void SetDescriptionTest() {
		Achievement achievement = new Achievement();
		achievement.setDescription("Test1");
		assertEquals("Test1", achievement.getDescription());
	}
	@Test
	void SetDescriptionTwiceTest() {
		Achievement achievement = new Achievement();
		achievement.setDescription("Test0");
		achievement.setDescription("Test1");
		assertEquals("Test1", achievement.getDescription());
	}
	@Test
	void SetValueTest(){
		Achievement achievement = new Achievement();
		achievement.setValue(300);
		assertEquals(300, achievement.getValue());
	}

	@Test
	void setCollectorIDTest(){
		Achievement achievement = new Achievement();
		achievement.setCollectorId("ThisIsMyCollectorId");
		assertEquals("ThisIsMyCollectorId", achievement.getCollectorId());
	}	
}
