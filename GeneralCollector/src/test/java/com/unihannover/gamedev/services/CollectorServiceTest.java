package com.unihannover.gamedev.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import com.unihannover.gamedev.CollectorConfig;
import com.unihannover.gamedev.CollectorConfigParser;
import com.unihannover.gamedev.models.Achievement;
import com.unihannover.gamedev.models.Collector;
import com.unihannover.gamedev.models.Model;
import com.unihannover.gamedev.models.UserAchievement;


class CollectorServiceTest {

	@Test
	void testInitCollector() {
		CollectorConfig collectorConfig = CollectorConfigParser.configJsonToObject();
		Collector me = new Collector();
		if(collectorConfig != null) {
			System.out.println("****Config details****"); 
			me.setName(collectorConfig.getName());
			me.setId(collectorConfig.getCollectorId());
			me.setToken(collectorConfig.getToken());
            System.out.println(me.getName());
		    System.out.println(me.getId());
		    System.out.println(me.getToken());
		    assertEquals(me.getName(), collectorConfig.getName());
		    assertEquals(me.getId(), collectorConfig.getCollectorId());
		    assertEquals(me.getToken(), collectorConfig.getToken());		    
		}
		collectorConfig = null;
		if(collectorConfig == null) {
			me.setName("GeneralCollector");
			me.setId("general-collector");
			me.setToken("supersecretToken");
			assertEquals(me.getName(), "GeneralCollector");
		    assertEquals(me.getId(), "general-collector");
		    assertEquals(me.getToken(), "supersecretToken");	
		}
	}
	
	@Test
	void initAchievements() {
		CollectorConfig config = CollectorConfigParser.configJsonToObject();
		List<Model> aList = new ArrayList<>();
		List<Model> uaList = new ArrayList<>();
		Achievement a1 = new Achievement();
		a1.setCollectorId(config.getCollectorId());
		a1.setId("c" + config.getCollectorId() + "-1");
		a1.setName("Test_Achievement1");
		a1.setDescription("This is a simple Test Achievement");
		a1.setValue(1);
		aList.add(a1);
		
		//tests
		assertEquals(a1.getId(), "cgit101-1");
		assertEquals(a1.getName(), "Test_Achievement1");
		
		Achievement a2 = new Achievement();
		a2.setCollectorId(config.getCollectorId());
		a2.setId("c" + config.getCollectorId() + "-2");
		a2.setName("Test_Achievement2");
		a2.setDescription("This is a simple Test Achievement 2");
		a2.setValue(3);
		aList.add(a2);
		
		assertEquals(a2.getId(), "cgit101-2");
		assertEquals(a2.getName(), "Test_Achievement2");
		
		Achievement a3 = new Achievement();
		a3.setCollectorId(config.getCollectorId());
		a3.setId("c" + config.getCollectorId() + "-3");
		a3.setName("Test_Achievement3");
		a3.setDescription("This is a simple Test Achievement 3");
		a3.setValue(10);
		aList.add(a3);
		
		assertEquals(a3.getId(), "cgit101-3");
		assertEquals(a3.getName(), "Test_Achievement3");

		UserAchievement ua1 = new UserAchievement();
		//questions ??
		/*user achievement Id seems to be the same as achievement id.
		* shouldn't it be unique foe each user ?? 
		*/
		ua1.setAchievementId("c" + config.getCollectorId() + "-1");
		ua1.setCollectorId(config.getCollectorId());
		ua1.setProgress(100f);
		ua1.setUserEmail("dev1@example.com");
		ua1.setLastUpdated(new Timestamp(System.currentTimeMillis()));
		uaList.add(ua1);
		
		
		assertEquals(ua1.getAchievementId(), "cgit101-1");
		assertEquals(ua1.getUserEmail(), "dev1@example.com");

		UserAchievement ua2 = new UserAchievement();
		ua2.setAchievementId("c" + config.getCollectorId() + "-2");
		ua2.setCollectorId(config.getCollectorId());
		ua2.setProgress(80f);
		ua2.setUserEmail("dev1@example.com");
		ua2.setLastUpdated(new Timestamp(System.currentTimeMillis()));
		uaList.add(ua2);
		
		assertEquals(ua2.getAchievementId(), "cgit101-2");
		assertEquals(ua2.getUserEmail(), "dev1@example.com");
		assertEquals(ua2.getProgress(), 80f);
		
		System.out.println("****User Achievements are added in a list****");
		for(Model model : uaList) {
			System.out.println(model.toJSON());
		}
		
		System.out.println("****Achievements are added in a list****");
		for(Model model : aList) {
			System.out.println(model.toJSON());
		}
	}
}
