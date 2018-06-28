package com.fundoonotes.user.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class LoginDTO {

	@NotEmpty
	@Email(message = "please enter a valid email")
	private String email;

	@NotEmpty
	@Length(min = 4, max = 40, message = "please enter a valid password")
	private String password;

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

}
