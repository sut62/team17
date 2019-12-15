package com.okta.springbootvue.repository.apple;

import com.okta.springbootvue.entity.apple.Pre_Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface Pre_OrderRepository extends JpaRepository<Pre_Order, Long> {
}