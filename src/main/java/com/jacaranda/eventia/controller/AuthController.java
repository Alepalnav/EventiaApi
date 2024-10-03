package com.jacaranda.eventia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.eventia.dto.LoginCredential;
import com.jacaranda.eventia.dto.RegisterDTO;
import com.jacaranda.eventia.dto.UserDTO;
import com.jacaranda.eventia.exception.ExceptionCredentialNotValid;
import com.jacaranda.eventia.exception.ExceptionDuplicatedEmail;
import com.jacaranda.eventia.model.User;
import com.jacaranda.eventia.service.UserService;
import com.jacaranda.eventia.utility.TokenUtils;

@RestController
public class AuthController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/register")
	public ResponseEntity<?> register(@RequestBody User user, BindingResult bindingResult) {
		user.setRole("user");
		try {
			RegisterDTO newUserDTO = userService.addUser(user);			
			if(newUserDTO==null) {
				throw new ExceptionDuplicatedEmail("Email duplicado");
			}else {
				return ResponseEntity.status(201).body(newUserDTO);
			}
		}catch(Exception e) {
			throw new ExceptionDuplicatedEmail(e.getMessage());
		}
		
	}
	
	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody LoginCredential loginRequest) {
		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
	
		} catch (Exception e) {
			throw new ExceptionCredentialNotValid(e.getMessage());
		}
		
		User user = (User)authentication.getPrincipal();
		String jwt = TokenUtils.generateToken(user.getId(),loginRequest.getEmail(), user.getEmail(), user.getRole());

		return ResponseEntity.ok(jwt);
		
	}
	

}
