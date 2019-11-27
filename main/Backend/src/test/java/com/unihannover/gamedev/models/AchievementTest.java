package com.unihannover.gamedev.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

class AchievementTest {

	@Test
	void SetNameTest() {
		Achievement ach = new Achievement();
		ach.setName("Test1");
		assertEquals("Test1", ach.getName());
	}
	@Test
	void SetNameTwiceTest() {
		Achievement ach = new Achievement();
		ach.setName("Test0");
		assertEquals("Test0", ach.getName());
		ach.setName("Test1");
		assertEquals("Test1", ach.getName());
	}
	@Test
	void SetDescriptionTest() {
		Achievement ach = new Achievement();
		ach.setDescription("Test1");
		assertEquals("Test1", ach.getDescription());
	}
	@Test
	void SetDescriptionTwiceTest() {
		Achievement ach = new Achievement();
		ach.setDescription("Test0");
		ach.setDescription("Test1");
		assertEquals("Test1", ach.getDescription());
	}
	@Test
	void SetValueTest(){
		Achievement ach = new Achievement();
		ach.setValue(300);
		assertEquals(300, ach.getValue());
	}

	@Test
	void setCollectorIDTest(){
		Achievement ach = new Achievement();
		ach.setCollectorId(1);
		assertEquals(1, ach.getCollectorId());
	}	
	
	@Ignore("function getId() is missing") @Test
	void getUniqueID(){
		//Achievement a1 = new Achievement();
		//Achievement a2 = new Achievement();
		//assertFalse(a1.getId == a2.getId);
	}

}
