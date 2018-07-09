package com.fundoonotes.repository;

import com.fundoonotes.model.User;

public interface UserRepository {

	void save(User user);

	void update(User user);

	void delete(Long id);
}
