package com.okta.springbootvue.repository.parn;

import com.okta.springbootvue.entity.parn.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public
interface ProductRepository extends JpaRepository<Product, Long> {
    Product findById(long id);
}