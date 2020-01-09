package com.okta.springbootvue.controller.apple;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.joy.*;
import com.okta.springbootvue.repository.joy.*;
import com.okta.springbootvue.entity.oil.*;
import com.okta.springbootvue.repository.oil.*;
import com.okta.springbootvue.entity.ta.*;
import com.okta.springbootvue.repository.ta.*;
import com.okta.springbootvue.entity.apple.*;
import com.okta.springbootvue.repository.apple.*;


import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class Pre_OrderController {
    @Autowired
    private final Pre_OrderRepository pre_orderRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private Type_ProductRepository type_productRepository;
    @Autowired
    private TitleNameRepository titlenameRepository;
    @Autowired
    private QuantityRepository quantityRepository;

    Pre_OrderController(Pre_OrderRepository pre_orderRepository) {
        this.pre_orderRepository = pre_orderRepository;
    }

    @GetMapping("/pre_order")
    public Collection<Pre_Order> pre_orders() {
        return pre_orderRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/pre_order/{employeeID}/{titlenameID}/{cus_name}/{tel}/{brandID}/{type_productID}/{quantityID}")
    public Pre_Order newPreOrder (Pre_Order newPreOrder,
    @PathVariable long employeeID,
    @PathVariable long titlenameID,
    @PathVariable String cus_name,
    @PathVariable String tel,
    @PathVariable long brandID,
    @PathVariable long type_productID,
    @PathVariable long quantityID) {

    TitleName titlename = titlenameRepository.findById(titlenameID);
    Brand brand = brandRepository.findById(brandID);
    Type_Product type_product = type_productRepository.findById(type_productID);
    Quantity quantity = quantityRepository.findById(quantityID);
    Employee employee = employeeRepository.findById(employeeID);
    LocalDateTime now = LocalDateTime.now();

    newPreOrder.setEmployee(employee);
    newPreOrder.setTitlename(titlename);
    newPreOrder.setCus_name(cus_name);
    newPreOrder.setTel(tel);
    newPreOrder.setBrand(brand);
    newPreOrder.setType_product(type_product);
    newPreOrder.setQuantity(quantity);
    newPreOrder.setDate(now);

    return pre_orderRepository.save(newPreOrder);
    
    }
}