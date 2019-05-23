package com.floe.springbootsecuritydemo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "Users")
public class User extends IdEntity {
	
	private String username;
	private String password;
	private String email;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	private boolean isEnable;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<Role> roles= new HashSet<>();

}