package com.jacaranda.eventia.dto;


public class RegisterDTO {
	
	private Integer id;
	
	private String name;
	
	private String email;
	
	private String role;
	
	

	public RegisterDTO(Integer id, String name, String email, String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	

}
