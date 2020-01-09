package com.okta.springbootvue.controller.apple;

import com.okta.springbootvue.entity.apple.Quantity;
import com.okta.springbootvue.repository.apple.QuantityRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class QuantityController {

    @Autowired
    private final QuantityRepository quantityRepository;

    public QuantityController(QuantityRepository quantityRepository) {
        this.quantityRepository = quantityRepository;
    }

    @GetMapping("/quantity")
    public Collection<Quantity> quantitys() {
        return quantityRepository.findAll().stream().collect(Collectors.toList());
    }
    
}