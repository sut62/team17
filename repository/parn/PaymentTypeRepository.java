package com.okta.springbootvue.repository.parn;

import com.okta.springbootvue.entity.parn.PaymentType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
	PaymentType findById(long id);
}