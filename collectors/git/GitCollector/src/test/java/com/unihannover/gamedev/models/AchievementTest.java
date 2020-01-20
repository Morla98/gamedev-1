package com.unihannover.gamedev.models;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AchievementTest {

		Achievement achv1 = new Achievement("101", "Ich bin in Urlaub", "Chill and dont commit for a week", 20f, "generalCollector") {
		};
		
		@Test
		void testToJSON() {
			String achv1JsonString = "{\"collectorId\":  \"generalCollector\",\"description\":  \"Chill and dont commit for a week\",\"id\": \"101\",\"name\": \"Ich bin in Urlaub\",\"value\": 20.0}";
			assertEquals(achv1JsonString, achv1.toJSON());
			
		}
		
		@Test
		void AchievementPkTest(){
			Achievement.AchievementPK achievementPK = new Achievement.AchievementPK("ThisIsMyId", "ThisIsMyCollectorId");
			assertEquals("ThisIsMyId", achievementPK.id);
			assertEquals("ThisIsMyCollectorId", achievementPK.collectorId);
		}
		
		@Test
		void testGetId() {
			assertEquals("101", achv1.getId());
			assertNotEquals(101, achv1.getId());
		}

		@Test
		void testSetId() {
			achv1.setId("102");
			assertEquals("102", achv1.getId());
			assertNotEquals(101, achv1.getId());
		}

		@Test
		void testGetCollectorId() {
			assertEquals("generalCollector", achv1.getCollectorId());
			assertNotEquals("GeneralCollector", achv1.getCollectorId());
		}

		@Test
		void testSetCollectorId() {
			achv1.setCollectorId("GeneralCollector");
			assertEquals("GeneralCollector", achv1.getCollectorId());
			assertNotEquals("generalCollector", achv1.getCollectorId());
		}

		@Test
		void testGetName() {
			assertEquals("Ich bin in Urlaub", achv1.getName());
		}

		@Test
		void testSetName() {
			achv1.setName("Im Urlaub Bruder");
			assertNotEquals("Ich bin in Urlaub", achv1.getName());
		}

		@Test
		void testGetDescription() {
			assertEquals("Chill and dont commit for a week", achv1.getDescription());
			assertNotEquals("Endlich in urlaub,...",  achv1.getDescription());
		}

		@Test
		void testSetDescription() {
			achv1.setDescription("Endlich in Urlaub, Ruhe für 2 WOche");
			assertEquals("Endlich in Urlaub, Ruhe für 2 WOche", achv1.getDescription());
			assertNotEquals("Endlich in urlaub,...",  achv1.getDescription());
		}

		@Test
		void testGetValue() {
			float value = 20f;
			assertEquals(value, achv1.getValue());
			assertNotEquals(20.5f, value, 0.01);
		}

		@Test
		void testSetValue() {
			achv1.setValue(25.5f);
			assertEquals(25.5f, achv1.getValue());
			assertNotEquals(25.9f, 25.5f, 0.01);
		}


}
