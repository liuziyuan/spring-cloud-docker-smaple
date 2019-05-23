package com.floe.springbootsecuritydemo.repository;

import org.springframework.data.repository.CrudRepository;

import com.floe.springbootsecuritydemo.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

}
