package com.example.demo.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.example.demo.response.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BankingAppAuthenticationEntryPoint implements AuthenticationEntryPoint {
	ObjectMapper objectMapper = new ObjectMapper();
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		ApiError apiError = new ApiError();
		
		apiError.setMessage(authException.getMessage());
		apiError.setHttpStatus(HttpStatus.UNAUTHORIZED);
		apiError.setLocalDateTime(LocalDateTime.now());
	
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		
		String jsonInString = objectMapper.writeValueAsString(apiError);
		
		PrintWriter printWriter = response.getWriter();
		printWriter.print(jsonInString);
		printWriter.flush();
		printWriter.close();
	}

}
