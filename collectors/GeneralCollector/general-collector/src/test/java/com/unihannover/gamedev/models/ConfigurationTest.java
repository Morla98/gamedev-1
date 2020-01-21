package com.unihannover.gamedev.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConfigurationTest {
	Configuration config = new Configuration();

	@Test
	void testSetName() {
		config.setName("GeneralCollector");
		assertEquals("GeneralCollector", config.getName());
	}

	@Test
	void testSetCollectorId() {
		config.setCollectorId("general");
		assertEquals("general", config.getCollectorId());
	}

	@Test
	void testSetToken() {
		config.setToken("eyJhbGciOiJIUzUxMiJ9.eyJ..._2EMhWWG_g");
		assertEquals("eyJhbGciOiJIUzUxMiJ9.eyJ..._2EMhWWG_g", config.getToken());
	}

	@Test
	void testSetMainApiUrl() {
		config.setMainApiUrl("http://devgame:8080/api");
		assertEquals("http://devgame:8080/api", config.getMainApiUrl());
	}

	@Test
	void testSetJwtSecret() {
		config.setJwtSecret("GamerControlDINGS");
		assertEquals("GamerControlDINGS", config.getJwtSecret());
	}

}
