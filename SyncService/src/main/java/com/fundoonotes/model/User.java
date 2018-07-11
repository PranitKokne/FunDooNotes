package com.fundoonotes.model;

public class User {

	private String id;
	private String name;
	private String email;
	private String password;
	private String phoneNumber;
	private boolean isVerified;
	private String operation;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public User(String id, String name, String email, String password, String phoneNumber, boolean isVerified,
			String operation) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.isVerified = isVerified;
		this.operation = operation;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phoneNumber="
				+ phoneNumber + ", isVerified=" + isVerified + ", operation=" + operation + "]";
	}

}
