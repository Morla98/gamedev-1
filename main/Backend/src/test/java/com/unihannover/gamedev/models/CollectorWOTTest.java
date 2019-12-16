package com.unihannover.gamedev.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

/**
 * JUnit-Tests for package models.
 * Checks, if the methods in CollectorWOT work correctly.
 */
class CollectorWOTTest{

	/**
	 * Tests if the Collector id (primary key) is set correctly.
	 */
	@Test
	void setIdTest(){
		Collector collector = new Collector();
		collector.setId("ThisIsMyId");
		assertEquals("ThisIsMyId", collector.getId());
	}

	/**
	 * Tests if the Collector Name is set correctly.
	 */
	@Test
	void setNameTest(){
		Collector collector = new Collector();
		collector.setName("Collector1");
		assertEquals("Collector1", collector.getName());
	}

	/**
	 * Tests if the Collector timestamp is set correctly.
	 */
	@Test
	void setLastSeenTest(){
		Collector collector = new Collector();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		collector.setLastSeen(timestamp);
		assertEquals(timestamp, collector.getLastSeen());
	}

	/**
	 * Tests if the Collector token is set correctly.
	 */
	@Test
	void setTokenTest(){
		Collector collector = new Collector();
		collector.setToken("MyToken");
		assertEquals("MyToken", collector.getToken());
	}
}
