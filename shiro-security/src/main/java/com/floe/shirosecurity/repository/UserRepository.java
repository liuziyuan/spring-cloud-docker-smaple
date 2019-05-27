package com.floe.shirosecurity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.floe.shirosecurity.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findFirstByUsername(String username);
}
