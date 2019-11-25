package com.unihannover.gamedev.restcontroller;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unihannover.gamedev.security.LdapAuthenticator;

@RestController
public class AuthenticationController {

	@Autowired
	LdapAuthenticator ldapauthenticator;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public Response login(@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password) {
		if (ldapauthenticator.performAuthentication(email, password)) {
			System.out.println("User authenticated");
		}
		return null;

	}

}