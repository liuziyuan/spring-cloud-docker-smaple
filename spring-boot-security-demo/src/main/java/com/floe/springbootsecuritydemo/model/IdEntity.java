package com.floe.springbootsecuritydemo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;


@MappedSuperclass

public class IdEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
    private Integer id;
}
