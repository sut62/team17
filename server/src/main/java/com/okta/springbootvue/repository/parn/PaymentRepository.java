package com.okta.springbootvue.repository.parn;

import com.okta.springbootvue.entity.parn.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.data.repository.query.Param;
//import java.util.Optional;
@RepositoryRestResource
public
interface PaymentRepository extends JpaRepository<Payment, Long> {
    
}