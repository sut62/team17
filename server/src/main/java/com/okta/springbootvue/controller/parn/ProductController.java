package com.okta.springbootvue.controller.parn;

import com.okta.springbootvue.entity.parn.*;
import com.okta.springbootvue.repository.parn.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class ProductController {

    @Autowired
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/product")
    public Collection<Product> Product() {
        return productRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/product/{id}")
    public Product getProductId(@PathVariable("id") Long id) {
         return productRepository.findById(id).get();
    }
} 
