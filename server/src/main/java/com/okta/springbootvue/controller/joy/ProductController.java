package com.okta.springbootvue.controller.joy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import java.time.LocalDateTime;

import com.okta.springbootvue.entity.joy.*;
import com.okta.springbootvue.repository.joy.*;
import com.okta.springbootvue.entity.oil.*;
import com.okta.springbootvue.repository.oil.*;



import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ProductController {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private Type_ProductRepository type_productRepository;
    

    ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @GetMapping("/product")
    public Collection<Product> Products() {
        return productRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/product/{employee_id}/{brand_id}/{type_product_id}/{quantity}/{price}")
    public Product newProduct(Product newProduct,
    @PathVariable long employee_id, 
    @PathVariable long brand_id,
    @PathVariable long type_product_id,
    @PathVariable Integer quantity,
    @PathVariable Float price) {
    //VideoRental newVideoRental = new VideoRental();

    Employee employee = employeeRepository.findById(employee_id);
    Brand brand = brandRepository.findById(brand_id);
    Type_Product type_product = type_productRepository.findById(type_product_id);
    LocalDateTime now = LocalDateTime.now(); 

    newProduct.setEmployee(employee);
    newProduct.setBrand(brand);
    newProduct.setType_product(type_product);
    newProduct.setQuantity(quantity);
    newProduct.setPrice(price);
    newProduct.setDate(now);

    return productRepository.save(newProduct); //บันทึก Objcet ชื่อ VideoRental
    
    }
}