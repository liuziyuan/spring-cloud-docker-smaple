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

import com.floe.shirosecurity.model.User;
import com.floe.shirosecurity.repository.UserRepository;
import com.floe.shirosecurity.service.AuthorizationService;

@RestController
@RequestMapping("api/userAuths")
public class UserAuthorizationController {

	@Autowired
	private AuthorizationService authorizationService;
	@Autowired
	private UserRepository userRepository;
	
	private Map<String, Object> map;
	
	@GetMapping("/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> getUserRoleAuth(@PathVariable Integer id){
		map = new HashMap<>();
		User user = userRepository.getOne(id);
		map.put("user", user);
		map.put("roles", user.getRoles());
		return map;
	}
	
	
	@PutMapping("/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> updateUserRoleAuth(@PathVariable Integer id, @RequestParam(value ="role_ids") List<Integer> role_ids) {
		map = new HashMap<>();
		User user = authorizationService.userAuthorized(id, role_ids);
		map.put("user", user);
		map.put("roles", user.getRoles());
		return map;
	}
}
