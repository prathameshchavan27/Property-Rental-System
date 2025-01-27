package com.prs.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prs.pojos.User;

public interface UserDao extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String username);

	
}
