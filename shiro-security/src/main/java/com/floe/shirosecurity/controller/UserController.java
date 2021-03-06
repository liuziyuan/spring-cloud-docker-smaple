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

import com.floe.shirosecurity.common.ResponseResult;
import com.floe.shirosecurity.common.ResponseResultUtil;
import com.floe.shirosecurity.model.User;
import com.floe.shirosecurity.repository.UserRepository;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	private Map<String, Object> map;
	
//	@RequiresPermissions("USER:SEARCH:ALL")
	@GetMapping("/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
    public ResponseResult<Map<String, Object>> getUser(@PathVariable Integer id) {
		map = new HashMap<>();
		map.put("user",this.userRepository.getOne(id));
        return ResponseResultUtil.buildSuccess(map);
    }
	
//	@RequiresPermissions("USER:SEARCH:ONE")
	@GetMapping("/")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseResult<Map<String, Object>> getUsers(){
		map = new HashMap<>();
		map.put("users", (List<User>) userRepository.findAll());
		return ResponseResultUtil.buildSuccess(map);
	}
	
//	@RequiresPermissions("USER:CREATE")
	@PostMapping("/")
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseResult<Map<String, Object>> newUser(@RequestBody User user) {
		map = new HashMap<>();
		map.put("user",userRepository.save(user));
		return ResponseResultUtil.buildSuccess(map);
	}
	
//	@RequiresPermissions("USER:UPDATE")
	@PutMapping("/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseResult<Map<String, Object>> updateUser(@PathVariable Integer id, @RequestBody User user) {
		map = new HashMap<>();
		if(id == user.getId()) {
			map.put("user", userRepository.saveAndFlush(user));
		}
		return ResponseResultUtil.buildSuccess(map);
	}
	
//	@RequiresPermissions("USER:DELETE")
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseResult deleteUser(@PathVariable Integer id) {
		map = new HashMap<>();
		userRepository.deleteById(id);
		return ResponseResultUtil.buildSuccess();
	}

	
}
