package com.okta.springbootvue.repository.ta;

import com.okta.springbootvue.entity.ta.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findById(long id);
    Customer findByTel(String tel);
}