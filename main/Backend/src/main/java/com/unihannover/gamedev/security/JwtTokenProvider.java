package com.unihannover.gamedev.security;

import java.nio.file.attribute.UserPrincipal;
import java.util.Date;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	//private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

	@Value("${app.jwtSecret}")
	private String jwtSecret;

	@Value("${app.jwtExpirationInMs}")
	private int jwtExpirationInMs;

	public String generateToken(Authentication authentication) {

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();


		//The long should be user Id which has to be added
		return Jwts.builder().setSubject(Long.toString(1234L)).setIssuedAt(new Date())
				.setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

		return Long.parseLong(claims.getSubject());
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException ex) {
			System.out.println("JwtTokenProvider: Invalid JWT signature");
			// logger.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			System.out.println("JwtTokenProvider: Invalid JWT token");
			// logger.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			System.out.println("JwtTokenProvider: Expired JWT token");
			// logger.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			// logger.error("Unsupported JWT token");
			System.out.println("JwtTokenProvider: Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			// logger.error("JWT claims string is empty.");
			System.out.println("JwtTokenProvider: JWT claims string is empty.");
		}
		return false;
	}
}
