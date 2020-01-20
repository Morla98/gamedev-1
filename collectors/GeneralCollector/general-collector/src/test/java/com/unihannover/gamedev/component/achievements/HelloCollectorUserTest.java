package com.unihannover.gamedev.component.achievements;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.unihannover.gamedev.models.Configuration;

class HelloCollectorUserTest {
	HelloCollectorUser helloCollectorUser = new HelloCollectorUser();
	
	/**
	 * Testing the values read from configuration and values set to collector are authentic
	 */
	@Test
	void testApplyConfiguration() {
		
		Configuration configuration = new Configuration();
		configuration.setCollectorId("GeneralCollector");
		helloCollectorUser.applyConfiguration(configuration);
		assertEquals("cGeneralCollector1", helloCollectorUser.getId());
		assertEquals("I am Alive", helloCollectorUser.getDescription());
		assertEquals(1f, helloCollectorUser.getValue());
		assertNotEquals(1.05, helloCollectorUser.getValue(), 0.01);
		
	}
}
