package com.okta.springbootvue.repository.joy;

import com.okta.springbootvue.entity.joy.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ProductRepository extends JpaRepository<Product, Long> {
	Product findById(long id);
}