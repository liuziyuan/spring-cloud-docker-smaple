package com.floe.shirosecurity.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	@JsonIgnore
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<Role> roles= new HashSet<>();

}
