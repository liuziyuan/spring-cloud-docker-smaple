package com.floe.springbootsecuritydemo.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.floe.springbootsecuritydemo.model.Role;
import com.floe.springbootsecuritydemo.model.User;
import com.floe.springbootsecuritydemo.repository.UserRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findFirstByUsername(username);
		Set<Role> roles = user.getRoles();
		if (user == null) {
            throw new UsernameNotFoundException(username);
        }
		Set<GrantedAuthority> auths = new HashSet<>();
		GrantedAuthority grantedAuthority;
		for (Role role : roles) {
			grantedAuthority = new SimpleGrantedAuthority(role.getCode());
			auths.add(grantedAuthority);
		}

		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				user.isEnable(),
				user.isAccountNonExpired(),
				user.isCredentialsNonExpired(),
				user.isAccountNonLocked(),
				auths);
	}

}
