package com.okta.springbootvue.repository.may;

import com.okta.springbootvue.entity.may.CollectPoint;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface CollectPointRepository extends JpaRepository<CollectPoint, Long> {
}