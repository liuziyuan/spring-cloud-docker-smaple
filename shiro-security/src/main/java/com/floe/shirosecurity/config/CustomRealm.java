package com.floe.shirosecurity.config;

import java.util.Set;
import java.util.stream.Collectors;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.floe.shirosecurity.model.Resource;
import com.floe.shirosecurity.model.Role;
import com.floe.shirosecurity.model.User;
import com.floe.shirosecurity.repository.RoleRepository;
import com.floe.shirosecurity.repository.UserRepository;

public class CustomRealm extends AuthorizingRealm {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		User user = userRepository.findFirstByUsername(username);
		Set<Role> roles = user.getRoles();
		Set<String> roleCodes = roles.stream().map(Role::getCode).collect(Collectors.toSet());
		Set<Resource> resources = roleRepository.findPermissionsByCodeNames(roles);
		resources.forEach(resource -> {
			info.addStringPermission(resource.getCode());
		});
		info.addRoles(roleCodes);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = token.getPrincipal().toString();
		User user = userRepository.findFirstByUsername(username);
		if (user != null) {
			// Object principal, Object credentials, String realmName AuthenticationInfo
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
			return authcInfo;
		} else {
			return null;
		}
	}

}
