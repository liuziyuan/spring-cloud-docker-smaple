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
@Table(name = "Roles")
public class Role extends IdEntity {
	private String code;
	private String name;
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="roles")
	private Set<User> users= new HashSet<>();
}
