package com.floe.shirosecurity.service;

import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.floe.shirosecurity.model.Resource;
import com.floe.shirosecurity.model.Role;
import com.floe.shirosecurity.model.User;
import com.floe.shirosecurity.repository.ResourceRepository;
import com.floe.shirosecurity.repository.RoleRepository;
import com.floe.shirosecurity.repository.UserRepository;

@Service
public class AuthorizationService {
	@Autowired
	private ResourceRepository resourceRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public Role roleAuthorized(Integer role_id, List<Integer> resource_ids) {
		Role role = roleRepository.getOne(role_id);
		List<Resource> permissions = resourceRepository.findAllById(resource_ids);
		role.setResources(new HashSet<>(permissions));
		role = roleRepository.saveAndFlush(role);
		return role;
		
	}
	
	@Transactional
	public User userAuthorized(Integer user_id, List<Integer> role_ids) {
		User user = userRepository.getOne(user_id);
		List<Role> roles = roleRepository.findAllById(role_ids);
		user.setRoles(new HashSet<Role>(roles));
		user = userRepository.saveAndFlush(user);
		return user;
		
	}
}
