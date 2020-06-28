package com.epam.userservice.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.epam.userservice.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public List<User> findByUserNameContains(String name);
}
