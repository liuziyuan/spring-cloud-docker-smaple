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

import com.floe.shirosecurity.model.Resource;
import com.floe.shirosecurity.repository.ResourceRepository;

@RestController
@RequestMapping("api/resources")
public class ResouceController {
	@Autowired
	private ResourceRepository resourceRepository;

	private Map<String, Object> map;
	
	@GetMapping("/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getResource(@PathVariable Integer id) {
		map = new HashMap<>();
		map.put("resource",this.resourceRepository.getOne(id));
        return map;
    }
	
	@GetMapping("/")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> getResource(){
		map = new HashMap<>();
		map.put("resources", (List<Resource>) resourceRepository.findAll());
		return map;
	}
	
	@PostMapping("/")
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, Object> newResource(@RequestBody Resource resource) {
		map = new HashMap<>();
		int count = resourceRepository.countByCode(resource.getCode());
		if(count == 0) {
			map.put("resource",resourceRepository.save(resource));
		}else {
			map.put("error", "Data existing");
		}
		
		return map;
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> editResource(@PathVariable Integer id, @RequestBody Resource resource) {
		map = new HashMap<>();
		if(id == resource.getId()) {
			int count = resourceRepository.countByCode(resource.getCode());
			if(count == 0) {
				map.put("resource", resourceRepository.saveAndFlush(resource));
			}
		}
		return map;
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> deleteResource(@PathVariable Integer id) {
		map = new HashMap<>();
		resourceRepository.deleteById(id);
		return map;
	}
}
