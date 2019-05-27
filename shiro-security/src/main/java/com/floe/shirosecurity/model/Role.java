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
@Table(name = "Roles")
public class Role extends IdEntity {
	private String code;
	private String name;
	@JsonIgnore
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<Permission> permissions= new HashSet<>();
	
}