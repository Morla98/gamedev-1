package com.unihannover.gamedev.security;

import com.unihannover.gamedev.services.ConfigurationService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    @Autowired
    private ConfigurationService configurationService;

    public String getCollectorIdFromJWT(String token) {
        String jwtSecret = configurationService.getConfig().getJwtSecret();
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            String jwtSecret = configurationService.getConfig().getJwtSecret();
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
