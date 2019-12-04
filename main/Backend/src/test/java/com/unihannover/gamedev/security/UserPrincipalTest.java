package com.unihannover.gamedev.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.unihannover.gamedev.models.User;


class UserPrincipalTest {
	
	
	
	@Test
	void testCreate() {
		User user = new User();
		user.setEmail("dev1@example.com");
		user.setUserName("devBond");
		System.out.println(user.getUserName());
		System.out.println(user.getEmail());
		
		UserPrincipal userPrincipal = UserPrincipal.create(user);
		System.out.println(userPrincipal.getUsername());
		assertEquals("devBond", userPrincipal.getUsername());
		assertEquals("dev1@example.com", userPrincipal.getEmail());
	}

	@Test
	void testGetUsername() {
		System.out.println("to be tested later. Method not yet implemented ");
	}

	

	@Test
	void testGetAuthorities() {
		System.out.println("to be tested later. Method not yet implemented ");
	}

	@Test
	void testGetPassword() {
		System.out.println("to be tested later. Method not yet implemented ");
	}

	@Test
	void testIsAccountNonExpired() {
		System.out.println("to be tested later. Method not yet implemented ");
	}

	@Test
	void testIsAccountNonLocked() {
		System.out.println("to be tested later. Method not yet implemented ");
	}

	@Test
	void testIsCredentialsNonExpired() {
		System.out.println("to be tested later. Method not yet implemented ");
	}

	@Test
	void testIsEnabled() {
		System.out.println("to be tested later. Method not yet implemented ");
	}

}
