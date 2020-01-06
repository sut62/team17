package com.okta.springbootvue.repository.ta;

import com.okta.springbootvue.entity.ta.TitleName;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface TitleNameRepository extends JpaRepository<TitleName, Long> {
	TitleName findById(long id);
}