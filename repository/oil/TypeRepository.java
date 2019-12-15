package com.okta.springbootvue.repository.oil;

import com.okta.springbootvue.entity.oil.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface TypeRepository extends JpaRepository<Type, Long> {
	Type findById(long id);
}