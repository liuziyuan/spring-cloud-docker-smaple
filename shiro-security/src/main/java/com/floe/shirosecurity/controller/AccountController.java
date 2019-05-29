package com.floe.shirosecurity.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

	private Map<String, Object> map;

	@PostMapping("/login")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> login(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		map = new HashMap<>();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(usernamePasswordToken); // 完成登录
//    		User user = (User) subject.getPrincipal();
			map.put("status", "LOGIN.SUCCESS");
		} catch (UnknownAccountException uaex) {
			map.put("error", "username or password error");
		} catch (IncorrectCredentialsException icex) {
			// TODO: handle exception
		} catch (LockedAccountException laex) {
			// TODO: handle exception
		} catch (ExcessiveAttemptsException eaex) {
			// TODO: handle exception
		} catch (AuthenticationException aex) {
			// TODO: handle exception
		}

		return map;
	}

	@PostMapping("/logout")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> logout() {
		map = new HashMap<>();
		SecurityUtils.getSubject().logout();
		map.put("status", "LOGOUT.SUCCESS");
		return map;
	}
}
