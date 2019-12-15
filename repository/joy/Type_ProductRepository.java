package com.okta.springbootvue.repository.joy;

import com.okta.springbootvue.entity.joy.Type_Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface Type_ProductRepository extends JpaRepository<Type_Product, Long> {
	Type_Product findById(long id);
}