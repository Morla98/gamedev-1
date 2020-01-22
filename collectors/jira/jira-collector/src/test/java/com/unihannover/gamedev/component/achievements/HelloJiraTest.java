package com.unihannover.gamedev.component.achievements;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.unihannover.gamedev.models.Configuration;

class HelloJiraTest {

	HelloJira helloJira = new HelloJira();
	
	@Test
	void testApplyConfiguration() {
		Configuration configuration = new Configuration();
		configuration.setCollectorId("JiraCollector");
		helloJira.applyConfiguration(configuration);
		
		//Achievement Id test 
		assertEquals("cJiraCollector1", helloJira.getId());
		assertNotEquals("cJiraCollector7", helloJira.getId());
		assertNotEquals("cjiraCollector1", helloJira.getId());
		
		//Description test 
		assertEquals("Create your first issue", helloJira.getDescription());
		assertNotEquals("create your first issue", helloJira.getDescription());
		
		//achievement values test 
		assertEquals(1f, helloJira.getValue());
		assertNotEquals(1.05, helloJira.getValue(), 0.01);
	}

}
