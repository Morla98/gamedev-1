package com.unihannover.gamedev.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.sql.Timestamp;

class UserAchievementTest {
	
	@Test
	void testToJSON() {
		//!attribute lastUpdated is not in JsonString, absichtlich...? 
		UserAchievement userAchievement = new UserAchievement();
		userAchievement.setAchievementId("ua101");
		userAchievement.setCollectorId("col001");
		userAchievement.setProgress(81f);
		userAchievement.setUserEmail("ken@chicken.com");
		String uaJsonString = "{\"achievementId\":  \"ua101\",\"collectorId\":  \"col001\",\"progress\": \"81.0\",\"userEmail\": \"ken@chicken.com\"}";
		assertEquals(uaJsonString, userAchievement.toJSON());
	}

	@Test
	void setUserEmailTest() {
		UserAchievement rel = new UserAchievement();
		rel.setUserEmail("abcdef123@adesso.de");
		assertEquals("abcdef123@adesso.de",rel.getUserEmail());
	}
	
	@Test
	void setAchievementIdTest() {
		UserAchievement rel = new UserAchievement();
		rel.setAchievementId("123IamId");
		assertEquals("123IamId",rel.getAchievementId());
	}
	
	@Test
	void setCollectorIdTest(){
		UserAchievement rel = new UserAchievement();
		rel.setCollectorId("123IamId");
		assertEquals("123IamId", rel.getCollectorId());
	}
	
	@Test
	void setProgress() {
		UserAchievement rel = new UserAchievement();
		rel.setProgress(50);
		assertEquals(50, rel.getProgress());
	}		
	@Test
	void setLastUpdatedTest() {
		UserAchievement rel = new UserAchievement();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		rel.setLastUpdated(timestamp);
		assertEquals(timestamp,rel.getLastUpdated());
	}	
	@Test
	void UserAchievementPKTest(){
		UserAchievement.UserAchievementPK  uapk= new UserAchievement.UserAchievementPK("MyAchievementId", "MyCollectorId", "MyUserEmail");
		assertEquals(uapk.achievementId, "MyAchievementId");
		assertEquals(uapk.collectorId, "MyCollectorId");
		assertEquals(uapk.userEmail, "MyUserEmail");

	}
}
