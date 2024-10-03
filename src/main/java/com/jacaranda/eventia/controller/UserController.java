package com.jacaranda.eventia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.eventia.dto.UserDTO;
import com.jacaranda.eventia.service.UserService;


@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping("/userByEmail")
	public List<UserDTO> listUsersemail(@RequestParam("email") String email){
		return userService.getUserByEmailDTO(email);
	}
	
}
