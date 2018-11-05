package com.nia.services.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.nia.services")
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	  private static final Logger logger = LogManager.getLogger(MyAuthenticationEntryPoint.class);

	  @Override
	  @ExceptionHandler (value = {AuthenticationException.class})
	  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
	      throws IOException, ServletException {
	    // 401
		logger.debug("401");
		System.out.println("401");
	    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
	  }

	  @ExceptionHandler (value = {AccessDeniedException.class})
	  public void commence(HttpServletRequest request, HttpServletResponse response,
	      AccessDeniedException accessDeniedException) throws IOException {
	    // 403
		  logger.debug("403");
		  System.out.println("403");
	    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authorization Failed : " + accessDeniedException.getMessage());
	  }

	  @ExceptionHandler (value = {Exception.class})
	  public void commence(HttpServletRequest request, HttpServletResponse response,
	      Exception exception) throws IOException {
	     // 500
		  logger.debug("500");
		  System.out.println("500");
	    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error : " + exception.getMessage());
	  }
	  
	  @ExceptionHandler (value = {UsernameNotFoundException.class})
	  public void commence(HttpServletRequest request, HttpServletResponse response,
			  UsernameNotFoundException exception) throws IOException {
	     // 500
		  logger.debug("501");
		  System.out.println("501");
	    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error : " + exception.getMessage());
	  }

}
