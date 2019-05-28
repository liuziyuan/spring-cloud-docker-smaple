package com.floe.shirosecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.floe.shirosecurity.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {
	Integer countByCode(String code);
}
