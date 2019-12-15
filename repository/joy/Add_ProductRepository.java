package com.okta.springbootvue.repository.joy;

import com.okta.springbootvue.entity.joy.Add_Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface Add_ProductRepository extends JpaRepository<Add_Product, Long> {
	Add_Product findById(long id);
}