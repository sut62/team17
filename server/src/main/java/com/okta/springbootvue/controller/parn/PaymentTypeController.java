package com.okta.springbootvue.controller.parn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.parn.*;
import com.okta.springbootvue.repository.parn.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PaymentTypeController {

    @Autowired
    private final PaymentTypeRepository paymentTypeRepository;

    public PaymentTypeController(PaymentTypeRepository paymentTypeRepository) {
        this.paymentTypeRepository = paymentTypeRepository;
    }

    @GetMapping("/paymentType")
    public Collection<PaymentType> PaymentType() {
        return paymentTypeRepository.findAll().stream().collect(Collectors.toList());
    }

} 
