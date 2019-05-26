package com.floe.springbootsecuritydemo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.floe.springbootsecuritydemo.model.Role;
import com.floe.springbootsecuritydemo.model.User;
import com.floe.springbootsecuritydemo.repository.RoleRepository;
import com.floe.springbootsecuritydemo.repository.UserRepository;

@RestController
public class UserController {

	
}
