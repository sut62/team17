package com.okta.springbootvue.repository.parn;

import com.okta.springbootvue.entity.parn.Add_Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public
interface Add_ProductRepository extends JpaRepository<Add_Product, Long> {
    Add_Product findById(long id);
}