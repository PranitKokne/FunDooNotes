package com.fundoonotes.user.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class UserDTO {

	@NotEmpty(message = "name cannot be null")
	private String name;
	
	@NotEmpty
	@Email(message = "please enter a valid email")
	private String email;
	
	@NotEmpty
	@Length(min = 4, max = 40, message = "please enter a valid password")
	private String password;
	
	@NotEmpty
	@Length(max = 14, message = "please enter a valid phone number")
	private String phoneNumber;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
