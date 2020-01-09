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
public class Add_ProductController {

    @Autowired
    private final Add_ProductRepository add_ProductRepository;

    public Add_ProductController(Add_ProductRepository add_ProductRepository) {
        this.add_ProductRepository = add_ProductRepository;
    }

    @GetMapping("/add_Product")
    public Collection<Add_Product> Add_Product() {
        return add_ProductRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/add_Product/{id}")
    public Add_Product getAdd_ProductId(@PathVariable("id") Long id) {
         return add_ProductRepository.findById(id).get();
    }

}

