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
	/*
	@Test
	void FirstNameEmptytest() {
		User user = new User();
		user.setFirstName("");
		fail("UserFirstName shouldnt be empty");		
	}*/
	@Test
	void LastNameTest() {
		User user = new User();
		user.setLastName("abc");
		assertEquals("abc", user.getLastName());		
	}
	/*
	@Test
	void LastNameEmptytest() {
		User user = new User();
		user.setLastName("");
		fail("UserLastName shouldnt be empty");		
	}*/
	@Test
	void FirstNameUsertest() {
		User user = new User();
		user.setUserName("abc");
		assertEquals("abc", user.getUserName());		
	}
	/*@Test
	void UserNameEmptytest() {
		User user = new User();
		user.setUserName("");
		fail("UserFullName shouldnt be empty");		
	}*/
	@Test
	void EmailTest(){
		User user = new User();
		user.setEmail("abc.def@adesso.de");
		assertEquals("abc.def@adesso.de", user.getEmail());
	}
	/*
	@Test
	void EmailFormTest(){
		User user = new User();
		user.setEmail("abc");
		fail("valid Email required");
	}
	@Test
	void EmailFormEmptyTest(){
		User user = new User();
		user.setEmail("");
		fail("valid Email required");
	}	
	@Test
	void EmailForm2Test(){
		User user = new User();
		user.setEmail("@adesso.de");
		fail("valid Email required");
	}*/
	@Test
	void setLevelTest() {
		User user = new User();
		user.setLevel(1);
		assertEquals(1,user.getLevel());
	}
	/*
	@Test 
	void setLevelZeroTest(){
		User user = new User();
		user.setLevel(0);
		assertEquals(0, user.getLevel());
	}
	@Test 
	void setLevelNegativeTest(){
		User user = new User();
		user.setLevel(-1);
		fail("level shouldnt be negative");
	}
	@Test
	void setLevelFloatTest() {
		User user = new User();
		user.setLevel(1);
		fail("Level is int");
	}
	*/
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