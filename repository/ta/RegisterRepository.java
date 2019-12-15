package com.okta.backend.repository;

import com.okta.backend.entity.ta.Register;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface RegisterRepository extends JpaRepository<Register, Long> {
}