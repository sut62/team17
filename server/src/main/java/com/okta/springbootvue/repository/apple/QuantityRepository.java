package com.okta.springbootvue.repository.apple;

import com.okta.springbootvue.entity.apple.Quantity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface QuantityRepository extends JpaRepository<Quantity, Long> {
	Quantity findById(long id);
}