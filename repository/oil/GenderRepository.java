package com.okta.springbootvue.repository.oil;

import com.okta.springbootvue.entity.oil.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface GenderRepository extends JpaRepository<Gender, Long> {
	Gender findById(long id);
}