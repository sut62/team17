package com.okta.springbootvue.repository.joy;

import com.okta.springbootvue.entity.joy.Brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findById(long id);
}