package com.floe.shirosecurity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.floe.shirosecurity.model.Role;
import com.floe.shirosecurity.repository.RoleRepository;
import com.floe.shirosecurity.service.AuthorizationService;

@RestController
@RequestMapping("api/roleAuthz")
public class RoleAuthzController {
	@Autowired
	private AuthorizationService authorizationService;
	@Autowired
	private RoleRepository roleRepository;
	
	private Map<String, Object> map;
	
	@GetMapping("/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> getRoleResourceAuthz(@PathVariable Integer id){
		map = new HashMap<>();
		Role role = roleRepository.getOne(id);
		map.put("role", role);
		map.put("permissions", role.getResources());
		return map;
	}
	
	
	@PutMapping("/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> updateRoleResourcesAuthz(@PathVariable Integer id, @RequestParam(value ="role_ids") List<Integer> role_ids) {
		map = new HashMap<>();
		Role role = authorizationService.roleAuthorized(id, role_ids);
		map.put("role", role);
		map.put("permissions", role.getResources());
		return map;
	}
}
