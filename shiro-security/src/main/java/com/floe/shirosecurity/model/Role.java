package com.floe.shirosecurity.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Roles")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Role extends IdEntity {
	private String code;
	private String name;
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "role_authz", 
	joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") },
	inverseJoinColumns = { @JoinColumn(name = "resource_id", referencedColumnName = "id") })
	private Set<Resource> resources = new HashSet<>();

}