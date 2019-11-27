package com.unihannover.gamedev.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserHasAchievementTest {

	@Test
	void setUserIdTest() {
		UserHasAchievement rel = new UserHasAchievement();
		rel.setUserId("abcdef123");
		assertEquals("abcdef123",rel.getUserId());
	}
	
	@Test
	void setAchievementIdTest() {
		UserHasAchievement rel = new UserHasAchievement();
		rel.setAchievementId(123);
		assertEquals(123,rel.getAchievementId());
	}
	
	@Test
	void setAchievementAccomplishedTrueTest() {
		UserHasAchievement rel = new UserHasAchievement();
		rel.setAchievementAccomplished(true);
		assertEquals(true, rel.isAchievementAccomplished());
	}	
	
	@Test
	void setAchievementAccomplishedFalseTest() {
		UserHasAchievement rel = new UserHasAchievement();
		rel.setAchievementAccomplished(false);
		assertEquals(false, rel.isAchievementAccomplished());
	}
	
	@Test
	void setCompletionRateTest() {
		UserHasAchievement rel = new UserHasAchievement();
		rel.setCompletionRate(100);
		assertEquals(100,rel.getCompletionRate());
	}	
}
