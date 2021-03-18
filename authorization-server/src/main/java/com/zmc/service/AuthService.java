package com.zmc.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmc.dto.AuthenticationResponse;
import com.zmc.dto.LoginRequest;
import com.zmc.dto.RegistrationRequest;
import com.zmc.jwtconfig.JWTProvider;
import com.zmc.models.User;
import com.zmc.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class AuthService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	private final JWTProvider jwtProvider;
	
	private ObjectMapper objectMapper;

	public String registerUser(RegistrationRequest registrationRequest) {
		try {
			User user=new User();
			user.setUserName(registrationRequest.getUserName());
			user.setPassword(encodePassword(registrationRequest.getPassword()));
			user.setEmail(registrationRequest.getEmail());
			user.setRoles(registrationRequest.getRoles());
			userRepository.save(user);
			return "User has been created sucessfully..........";
		}catch (Exception e) {
			return "already user exit with these name"+registrationRequest.getUserName();
		}
	}

	public String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}


	public AuthenticationResponse loginUser(LoginRequest loginRequest) throws JsonProcessingException {
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
				loginRequest.getPassword()));
		log.info("authentication obj {}"+ objectMapper.writeValueAsString(authentication));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token= jwtProvider.generateToken(authentication);
		log.info("authentication token{}"+token);
		return new AuthenticationResponse(token,loginRequest.getUserName());
	}

	public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
				getContext().getAuthentication().getPrincipal();
		return Optional.of(principal);
	}
}
