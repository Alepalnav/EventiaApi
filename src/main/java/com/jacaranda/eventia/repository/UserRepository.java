package com.jacaranda.eventia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.eventia.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	List<User> findByName(String username);
	
	List<User> findByEmail(String email);


}
