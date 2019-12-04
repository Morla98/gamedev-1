package com.unihannover.gamedev.models;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class CollectorWOTTest {
	CollectorWOT colWot = new CollectorWOT();

	@Test
	void testSetId() {
		colWot.setId("dev12469");
		assertEquals("dev12469", colWot.getId());
		
	}


	@Test
	void testSetName() {
		colWot.setName("Dev Bond");
		assertEquals("Dev Bond", colWot.getName());
	}

	
	@Test
	void testSetToken() {
		colWot.setToken("irgend3twasKom1sh3s");
		assertEquals("irgend3twasKom1sh3s", colWot.getToken());
	}

}
