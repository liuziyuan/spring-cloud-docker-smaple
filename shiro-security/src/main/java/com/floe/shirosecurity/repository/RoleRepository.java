package com.floe.shirosecurity.repository;


import java.util.Collection;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.floe.shirosecurity.model.Resource;
import com.floe.shirosecurity.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Integer countByCode(String code);
	
	@Query("select r from Resource r inner join r.roles role where role in (:roles)")
	Set<Resource> findPermissionsByCodeNames(@Param("roles") Collection<Role> roles);
}
