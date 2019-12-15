package com.okta.springbootvue.repository.may;

import com.okta.springbootvue.entity.may.PointPrice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface PointPriceRepository extends JpaRepository<PointPrice, Long> {
	PointPrice findById(long id);
}