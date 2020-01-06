package com.okta.springbootvue.controller.joy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.joy.Type_Product;
import com.okta.springbootvue.repository.joy.Type_ProductRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class Type_ProductController {

    @Autowired
    private final Type_ProductRepository type_productRepository;

    public Type_ProductController(Type_ProductRepository type_productRepository) {
        this.type_productRepository = type_productRepository;
    }

    @GetMapping("/type_Product")
    public Collection<Type_Product> Type_Products() {
        return type_productRepository.findAll().stream().collect(Collectors.toList());
    }

}