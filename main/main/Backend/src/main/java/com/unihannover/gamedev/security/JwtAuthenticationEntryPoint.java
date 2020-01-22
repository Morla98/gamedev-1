package com.unihannover.gamedev.security;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 *  
 *
 * @author Dominik Andrae
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	// TODO: Logger einbauen
    // private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        //logger.error("Responding with unauthorized error. Message - {}", e.getMessage());
    	System.out.println("Responding with unauthorized error. Message - {}" + e.getMessage());
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
    }
}
