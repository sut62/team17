package com.okta.springbootvue.repository.may;

import com.okta.springbootvue.entity.may.CollectPoint;
import com.okta.springbootvue.entity.parn.Payment;
import com.okta.springbootvue.entity.ta.Customer;
import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface CollectPointRepository extends JpaRepository<CollectPoint, Long> {
    CollectPoint findCollectByPayment(Payment payment);
    Collection<CollectPoint> findCollectByCustomer(Collection<Customer> customer);
}