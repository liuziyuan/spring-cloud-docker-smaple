package com.floe.shirosecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.floe.shirosecurity.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Integer countByCode(String code);
	
}
