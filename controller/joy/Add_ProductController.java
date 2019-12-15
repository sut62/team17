package com.okta.springbootvue.controller.joy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.time.LocalDateTime;

import com.okta.springbootvue.entity.joy.*;
import com.okta.springbootvue.repository.joy.*;
import com.okta.springbootvue.entity.oil.*;
import com.okta.springbootvue.repository.oil.*;



import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class Add_ProductController {
    @Autowired
    private final Add_ProductRepository add_productRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private Type_ProductRepository type_productRepository;
    

    Add_ProductController(Add_ProductRepository add_productRepository) {
        this.add_productRepository = add_productRepository;
    }


    @PostMapping("/add_product/{employee_id}/{brand_id}/{type_product_id}/{quantity}/{price}")
    public Add_Product newAdd_Product(Add_Product newAdd_Product,
    @PathVariable long employee_id, 
    @PathVariable long brand_id,
    @PathVariable long type_product_id,
    @PathVariable Integer quantity,
    @PathVariable Integer price) {
    //VideoRental newVideoRental = new VideoRental();

    Employee employee = employeeRepository.findById(employee_id);
    Brand brand = brandRepository.findById(brand_id);
    Type_Product type_product = type_productRepository.findById(type_product_id);
    LocalDateTime now = LocalDateTime.now(); 

    newAdd_Product.setEmployee(employee);
    newAdd_Product.setBrand(brand);
    newAdd_Product.setType_product(type_product);
    newAdd_Product.setQuantity(quantity);
    newAdd_Product.setPrice(price);
    newAdd_Product.setDate(now);

    return add_productRepository.save(newAdd_Product); //บันทึก Objcet ชื่อ VideoRental
    
    }
}