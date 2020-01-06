package com.okta.springbootvue.repository.ta;

import com.okta.springbootvue.entity.ta.Register;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface RegisterRepository extends JpaRepository<Register, Long> {
    Register findById(long id);
}