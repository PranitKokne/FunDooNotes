package com.redis.cache.model;

import java.io.Serializable;

public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String firstName;
	private String lastName;
	private String profile;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", profile=" + profile
				+ "]";
	}

	public Customer(String id, String firstName, String lastName, String profile) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profile = profile;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

}
