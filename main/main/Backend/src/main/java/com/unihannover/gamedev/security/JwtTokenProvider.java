package com.unihannover.gamedev.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 *	This Service generates and validates Jwt Tokens used in Authentication for User and Collectors
 *
 * @author Dominit Andrae
 */
@Component
public class JwtTokenProvider {

	//private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

	@Value("${app.jwtSecret}")
	private String jwtSecret;
	
	@Value("${app.jwtCollectorSecret}")
	private String jwtCollectorSecret;

	@Value("${app.jwtExpirationInMs}")
	private int jwtExpirationInMs;

	
	
	/**
	 * This Method generates a Token for an User
	 * @param email
	 * @return Jwt Token
	 */
	public String generateToken(String email) {

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

		return Jwts.builder().setSubject(email).setIssuedAt(new Date())
				.setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
	
	
	
	/**
	 * This Method generates a Token for a Collector using a different Secret and no Expiration Date
	 * @param id
	 * @param secret
	 * @return Jwt Token
	 */
	public String generateTokenWithSecretAndId(String id, String secret) {

		return Jwts.builder().setSubject(id).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public String getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

		return claims.getSubject();
	}

	/**
	 * Validates a User Token and checks for Signature, Expiration, Token Type and Claim
	 * @param authToken
	 * @return true if the Token is valid
	 */
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
	
	/**
	 * Validates a Collector Token and checks for Signature, Expiration, Token Type and Claim
	 * 
	 * @param authToken
	 * @return true if the Token is valid
	 */
	public boolean validateCollectorToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtCollectorSecret).parseClaimsJws(authToken);
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
