package com.fundoonotes.user.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class LoginDTO {

	@NotEmpty
	@Pattern(regexp="[a-zA-Z0-9][a-zA-Z0-9_.]*@gmail[.]com",message="Enter a valid email address")
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
