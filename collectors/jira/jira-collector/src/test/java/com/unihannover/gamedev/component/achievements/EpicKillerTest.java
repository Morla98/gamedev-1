package com.unihannover.gamedev.component.achievements;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.unihannover.gamedev.models.Configuration;

class EpicKillerTest {
	
	EpicKiller epicKiller =new EpicKiller();
	
	@Test
	void testApplyConfiguration() {
		Configuration configuration = new Configuration();
		configuration.setCollectorId("JiraCollector");
		epicKiller.applyConfiguration(configuration);
		
		//Achievement Id test 
		assertEquals("cJiraCollector7", epicKiller.getId());
		assertNotEquals("cJiraCollector1", epicKiller.getId());
		assertNotEquals("cjiraCollector7", epicKiller.getId());
		
		//Description test 
		assertEquals("Complete 10 Epics", epicKiller.getDescription());
		assertNotEquals("complete 10 Epics", epicKiller.getDescription());
		
		//achievement values test 
		assertEquals(10f, epicKiller.getValue());
		assertNotEquals(10.05, epicKiller.getValue(), 0.01);
	}

}
