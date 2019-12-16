package com.unihannover.gamedev.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * JUnit-Tests for package models.
 * Checks, if the methods in User work correctly.
 */
class UserTest {

	/**
	 * Tests, if the full name of a User is set correctly.
	 */
	@Test
	void setFullNameTest() {
		User user = new User();
		user.setFullName("Max Mustermann");
		assertEquals("Max Mustermann", user.getFullName());
	}

	/**
	 * Tests, if the user name of a User is set correctly.
	 */
	@Test
	void setUserNametest() {
		User user = new User();
		user.setUserName("abc");
		assertEquals("abc", user.getUserName());
	}

	/**
	 * Tests, if the E-Mail adress (primary key) of a User is set correctly.
	 */
	@Test
	void EmailTest(){
		User user = new User();
		user.setEmail("abc.def@adesso.de");
		assertEquals("abc.def@adesso.de", user.getEmail());
	}

	/**
	 * Tests, if the user id of a User is set correctly.
	 */
	@Test
	void setUIDTest(){
		User user = new User();
		user.setUid("MyUniqueID");
		assertEquals("MyUniqueID", user.getUid());
	}

	/**
	 * Tests, if the level of a User is set correctly.
	 */
	@Test
	void setLevelTest() {
		User user = new User();
		user.setLevel(1);
		assertEquals(1,user.getLevel());
	}

	/**
	 * Tests, if the score of a User is set to 0 correctly.
	 */
	@Test
	void setScoreZeroTest() {
		User user = new User();
		user.setScore(0);
		assertEquals(0,user.getScore());
	}

	/**
	 * Tests, if the score of a User is set with a negative integer correctly.
	 */
	@Test
	void setScoreNegativeTest() {
		User user = new User();
		user.setScore(-1);
		assertEquals(-1, user.getScore());
	}

	/**
	 * Tests, if the score of a User is set with a positive integer correctly.
	 */
	@Test
	void setScorePositiveTest() {
		User user = new User();
		user.setScore(100);
		assertEquals(100, user.getScore());
	}

	/**
	 * Tests, if the User is set anonymous correctly.
	 */
	@Test
	void setAnonymousTrue() {
		User user = new User();
		user.setAnonymous(true);
		assertTrue(user.isAnonymous());
	}

	/**
	 * Tests, if the User is set unanonymous correctly.
	 */
	@Test
	void setAnonymousFalse() {
		User user = new User();
		user.setAnonymous(false);
		assertFalse(user.isAnonymous());
	}
}