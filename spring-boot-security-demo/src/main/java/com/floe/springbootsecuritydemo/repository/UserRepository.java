package com.floe.springbootsecuritydemo.repository;

import org.springframework.data.repository.CrudRepository;
import com.floe.springbootsecuritydemo.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findFirstByUsername(String username);
}
