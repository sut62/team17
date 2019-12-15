package com.okta.springbootvue.repository.apple;

import com.okta.springbootvue.entity.apple.Number;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface NumberRepository extends JpaRepository<Number, Long> {
	Number findById(long id);
}