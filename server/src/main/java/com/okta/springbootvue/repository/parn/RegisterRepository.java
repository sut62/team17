package com.okta.springbootvue.repository.parn;

import com.okta.springbootvue.entity.parn.Register;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface RegisterRepository extends JpaRepository<Register, Long> {
	Register findById(long id);
}