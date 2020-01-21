package com.unihannover.gamedev.component.achievements;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.unihannover.gamedev.models.Configuration;

class DivideEtImperaTest {
	
	DivideEtImpera divNConq = new DivideEtImpera();
	
	@Test
	void testApplyConfiguration() {
		Configuration configuration = new Configuration();
		configuration.setCollectorId("JiraCollector");
		divNConq.applyConfiguration(configuration);
		
		//Achievement Id test 
		assertEquals("cJiraCollector9", divNConq.getId());
		assertNotEquals("cJiraCollector1", divNConq.getId());
		
		//Description test 
		assertEquals("Create 10 sub-tasks", divNConq.getDescription());
		
		//achievement values test 
		assertEquals(10f, divNConq.getValue());
		assertNotEquals(1.05, divNConq.getValue(), 0.01);
	}

	

}
