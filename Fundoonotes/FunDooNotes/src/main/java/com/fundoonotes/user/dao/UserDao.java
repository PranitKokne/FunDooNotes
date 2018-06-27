package com.fundoonotes.user.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.fundoonotes.user.model.User;

@Repository
public interface UserDao extends CrudRepository<User, String> {
	
	public User getByEmail(String email);
}
