package com.okta.backend.repository;

import com.okta.backend.entity.ta.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Employee findById(long id);
}