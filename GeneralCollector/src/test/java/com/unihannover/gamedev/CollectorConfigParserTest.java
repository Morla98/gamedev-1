package com.unihannover.gamedev;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CollectorConfigParserTest {

	@Test
	void testConfigCollectorToJson() {
		System.out.println("****test case 1 preview****");
	    CollectorConfig collectorConfig = new CollectorConfig("Genreal Collector", "gen101", "Ken, Chicken");
		String jsonString = "{\"name\":\"Genreal Collector\",\"collectorId\":\"gen101\",\"token\":\"Ken, Chicken\"}";
		System.out.println(CollectorConfigParser.configCollectorToJson(collectorConfig));
		System.out.println(jsonString + "\n");
		assertEquals(jsonString.equals(CollectorConfigParser.configCollectorToJson(collectorConfig)),true);
		
		//case 2 
		System.out.println("****test case 2 preview****");
		CollectorConfig collectorConfig2 = new CollectorConfig("Genreal Collectorrrr", "gen101", "Ken, Chicken");
		String jsonString2 = "{\"name\":\"Genreal Collector\",\"collectorId\":\"gen101\",\"token\":\"Ken, Chicken\"}";
		System.out.println(CollectorConfigParser.configCollectorToJson(collectorConfig2));
		System.out.println(jsonString2 + "\n");
		assertEquals(jsonString.equals(CollectorConfigParser.configCollectorToJson(collectorConfig2)),false);
	}



}
