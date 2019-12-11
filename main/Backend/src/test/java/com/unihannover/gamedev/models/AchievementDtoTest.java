package com.unihannover.gamedev.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.sql.Timestamp;

class AchievementDtoTest {

	@Test
	void setUserEmailTest() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		AchievementDto rel = new AchievementDto("MyCollectorId","collectorName", "description","email",1, timestamp, 2);
		rel.setUserEmail("abcdef123@adesso.de");
		assertEquals("abcdef123@adesso.de",rel.getUserEmail());
	}
	
	@Test
	void setCollectorIdTest(){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		AchievementDto rel = new AchievementDto("MyCollectorId","collectorName", "description","email",1, timestamp, 2);
		rel.setCollectorId("123IamId");
		assertEquals("123IamId", rel.getCollectorId());
	}
	
	@Test
	void setProgress() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		AchievementDto rel = new AchievementDto("MyCollectorId","collectorName", "description","email",1, timestamp, 2);
		rel.setProgress(50);
		assertEquals(50, rel.getProgress());
	}		
	@Test
	void setLastUpdatedTest() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		AchievementDto rel = new AchievementDto("MyCollectorId","collectorName", "description","email",1, timestamp, 2);
		Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
		rel.setLastUpdated(timestamp1);
		assertEquals(timestamp1,rel.getLastUpdated());
	}	
	@Test
	void UserAchievementDtoTest(){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		AchievementDto uapk = new AchievementDto("MyCollectorId","collectorName", "description","email",1, timestamp, 2);
		assertEquals(uapk.getName(), "collectorName");
		assertEquals(uapk.getCollectorId(), "MyCollectorId");
		assertEquals(uapk.getUserEmail(), "email");
		assertEquals(uapk.getDescription(), "description");
		assertEquals(uapk.getValue(), 2);
		assertEquals(uapk.getLastUpdated(), timestamp);
		assertEquals(uapk.getProgress(), 1);
	}
}
