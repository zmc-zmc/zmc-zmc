package com.zmc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmc.dto.AuthenticationResponse;
import com.zmc.dto.LoginRequest;
import com.zmc.dto.RegistrationRequest;
import com.zmc.service.AuthService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

	private final AuthService authService;

	private final ObjectMapper objectMapper;
	
	
	@PostMapping(path = "/signUp",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) {
		String resp=authService.registerUser(registrationRequest);
		return new ResponseEntity<String>(resp,HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<AuthenticationResponse> loginUser(@RequestBody LoginRequest loginRequest) throws JsonProcessingException{
		log.info("login object from request{}"+objectMapper.writeValueAsString(loginRequest));
		AuthenticationResponse authenticationResponse=authService.loginUser(loginRequest);
		return new ResponseEntity<AuthenticationResponse>(authenticationResponse,HttpStatus.OK);
	}
}
