package com.okta.springbootvue.controller.apple;

import com.okta.springbootvue.entity.apple.Number;
import com.okta.springbootvue.repository.apple.NumberRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class NumberController {

    @Autowired
    private final NumberRepository numberRepository;

    public NumberController(NumberRepository numberRepository) {
        this.numberRepository = numberRepository;
    }

    @GetMapping("/number")
    public Collection<Number> numbers() {
        return numberRepository.findAll().stream().collect(Collectors.toList());
    }
    
}