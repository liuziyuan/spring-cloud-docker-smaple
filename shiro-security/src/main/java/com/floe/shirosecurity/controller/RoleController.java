package com.floe.shirosecurity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.floe.shirosecurity.model.Role;
import com.floe.shirosecurity.repository.RoleRepository;

@RestController
@RequestMapping("api/roles")
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;
	private Map<String, Object> map;
	
	@GetMapping("/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getRole(@PathVariable Integer id) {
		map = new HashMap<>();
		map.put("role",roleRepository.getOne(id));
        return map;
    }
	
	@GetMapping("/")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> getRoles(){
		map = new HashMap<>();
		map.put("roles", (List<Role>) roleRepository.findAll());
		return map;
	}
	
	@PostMapping("/")
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, Object> newRole(@RequestBody Role role) {
		map = new HashMap<>();
		int count = roleRepository.countByCode(role.getCode());
		if(count == 0) {
			map.put("role",roleRepository.save(role));
		}else {
			map.put("error", "Data existing");
		}
		return map;
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editRole(@PathVariable Integer id, @RequestBody Role role) {
		map = new HashMap<>();
		if(id == role.getId()) {
			map.put("role", roleRepository.saveAndFlush(role));
		}
		return map;
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> deleteRole(@PathVariable Integer id) {
		map = new HashMap<>();
		roleRepository.deleteById(id);
		return map;
	}
	
}
