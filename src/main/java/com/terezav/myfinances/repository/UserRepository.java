package com.terezav.myfinances.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.terezav.myfinances.data.model.User;

public interface UserRepository 
						extends JpaRepository<User, Long>{
	
	User findByEmail(String email);
	
	User findByLogin(String login);

}
