package com.unihannover.gamedev.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

class CollectorTest{
	

	@Test
	void testToJSON() {
		Collector collector = new Collector();
		collector.setId("ThisIsMyId");
		collector.setName("Collector1");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		collector.setLastSeen(timestamp);
		collector.setToken("MyToken");
		String collectorJsonString = "{\"id\": \"ThisIsMyId\", \"name\": \"Collector1\",\"token\": \"MyToken\"}";
		assertEquals(collectorJsonString, collector.toJSON());
		
	}
	
	
	@Test
	void setIdTest(){
		Collector collector = new Collector();
		collector.setId("ThisIsMyId");
		assertEquals("ThisIsMyId", collector.getId());
	}
	
	@Test
	void setNameTest(){
		Collector collector = new Collector();
		collector.setName("Collector1");
		assertEquals("Collector1", collector.getName());
	}
	
	@Test
	void setLastSeenTest(){
		Collector collector = new Collector();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		collector.setLastSeen(timestamp);
		assertEquals(timestamp, collector.getLastSeen());
	}
	
	@Test
	void setTokenTest(){
		Collector collector = new Collector();
		collector.setToken("MyToken");
		assertEquals("MyToken", collector.getToken());
	}
}