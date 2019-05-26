package com.floe.springbootsecuritydemo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

//@Data
//@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "Roles")
public class Role extends IdEntity {
	private String code;
	private String name;
	@JsonIgnore
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="roles")
	private Set<User> users= new HashSet<>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}
	
	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
