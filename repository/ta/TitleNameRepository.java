package com.okta.backend.repository;

import com.okta.backend.entity.ta.TitleName;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface TitleNameRepository extends JpaRepository<TitleName, Long> {
	TitleName findById(long id);
}