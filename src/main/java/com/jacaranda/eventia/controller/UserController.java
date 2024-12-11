package com.jacaranda.eventia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.eventia.dto.UserDTO;
import com.jacaranda.eventia.exception.ElementNotFoundException;
import com.jacaranda.eventia.service.UserService;


@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping("/userByEmail")
	public List<UserDTO> listUsersemail(@RequestParam("email") String email){
		return userService.getUserByEmailDTO(email);
	}
	
	@GetMapping("/userById/{id}")
	public ResponseEntity<?> userById(@PathVariable Integer id){
		String idUser = String.valueOf(id);
		UserDTO userDTO = userService.getUser(id);
		if(userDTO==null) {
			throw new ElementNotFoundException(idUser);
		}else {
			return ResponseEntity.ok(userDTO);
		}
	}
	
	@GetMapping("/users")
	public List<UserDTO> listUsers(){
		return userService.getUsers();
	}
	
	@GetMapping("/changeRol/{id}")
	public ResponseEntity<?> changeRol(@PathVariable Integer id){
		UserDTO userDTO = userService.changeRol(id);
		String idUser = String.valueOf(id);
		if(userDTO==null) {
			throw new ElementNotFoundException(idUser);
		}else {
			return ResponseEntity.ok(userDTO);
		}
	}
	
	
}
