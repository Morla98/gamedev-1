package com.unihannover.gamedev.restcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unihannover.gamedev.security.JwtTokenProvider;
import com.unihannover.gamedev.security.LdapAuthenticator;

@RestController
public class AuthenticationController extends BaseController {

	@Autowired
	LdapAuthenticator ldapauthenticator;
	
	@Autowired
	JwtTokenProvider tokenProvider;

	@RequestMapping(value = "/auth/login", method = RequestMethod.GET)
	public ResponseEntity<String> login(@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password) {
		if (ldapauthenticator.performAuthentication(email, password)) {
			String token = tokenProvider.generateToken(email);
			return new ResponseEntity<>(token, HttpStatus.OK);
		}
		return new ResponseEntity<>("not authorized", HttpStatus.UNAUTHORIZED);

	}

}