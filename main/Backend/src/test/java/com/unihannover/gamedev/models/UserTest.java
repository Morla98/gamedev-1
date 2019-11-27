package com.unihannover.gamedev.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	void FirstNameTest() {
		User user = new User();
		user.setFirstName("abc");
		assertEquals("abc", user.getFirstName());		
	}
	
	@Test
	void LastNameTest() {
		User user = new User();
		user.setLastName("abc");
		assertEquals("abc", user.getLastName());		
	}
	
	@Test
	void FirstNameUsertest() {
		User user = new User();
		user.setUserName("abc");
		assertEquals("abc", user.getUserName());		
	}
	
	@Test
	void EmailTest(){
		User user = new User();
		user.setEmail("abc.def@adesso.de");
		assertEquals("abc.def@adesso.de", user.getEmail());
	}
	
	@Test
	void setLevelTest() {
		User user = new User();
		user.setLevel(1);
		assertEquals(1,user.getLevel());
	}

	@Test 
	void setScoreZeroTest() {
		User user = new User();
		user.setScore(0);
		assertEquals(0,user.getScore());
	}
	
	@Test
	void setScoreNegativeTest() {
		User user = new User();
		user.setScore(-1);
		assertEquals(-1, user.getScore());
	}
	
	@Test
	void setScorePositiveTest() {
		User user = new User();
		user.setScore(100);
		assertEquals(100, user.getScore());
	}
	
	@Test
	void setAnonymousTrue() {
		User user = new User();
		user.setAnonymous(true);
		assertTrue(user.isAnonymous());
	}
	
	@Test
	void setAnonymousFalse() {
		User user = new User();
		user.setAnonymous(false);
		assertFalse(user.isAnonymous());
	}
}