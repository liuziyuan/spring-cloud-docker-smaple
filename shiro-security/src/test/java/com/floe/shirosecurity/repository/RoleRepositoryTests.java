package com.floe.shirosecurity.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.floe.shirosecurity.model.Resource;
import com.floe.shirosecurity.model.Role;
import com.floe.shirosecurity.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleRepositoryTests {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Test
	@Transactional
	public void findPermissionsByCodeNamesTests() {
		User user = userRepository.findFirstByUsername("admin");
		List<Role> roleList = roleRepository.findAll();
		Set<Role> roles = user.getRoles();
		Set<Resource> resources = roleRepository.findPermissionsByCodeNames(roleList);
		assertTrue(resources.size() > 0);
	}
}
