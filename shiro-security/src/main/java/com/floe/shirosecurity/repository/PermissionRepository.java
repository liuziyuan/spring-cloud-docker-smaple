package com.floe.shirosecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.floe.shirosecurity.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

}
