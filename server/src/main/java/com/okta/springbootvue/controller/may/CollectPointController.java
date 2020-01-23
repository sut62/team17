package com.okta.springbootvue.controller.may;

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

import com.okta.springbootvue.entity.may.*;
import com.okta.springbootvue.entity.ta.Customer;
import com.okta.springbootvue.entity.oil.Employee;
import com.okta.springbootvue.entity.parn.Payment;

import com.okta.springbootvue.repository.may.*;
import com.okta.springbootvue.repository.ta.CustomerRepository;
import com.okta.springbootvue.repository.oil.EmployeeRepository;
import com.okta.springbootvue.repository.parn.PaymentRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class CollectPointController {
    @Autowired
    private final CollectPointRepository collectPointRepository;
    @Autowired
    private PointPriceRepository pointPriceRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    

    CollectPointController(CollectPointRepository collectPointRepository) {
        this.collectPointRepository = collectPointRepository;
    }

	@GetMapping("/collectPoint")
    public Collection<CollectPoint> CollectPoints() {
        return collectPointRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/collectPoint/{payment}")
    public CollectPoint getCollectByPayment(@PathVariable long payment) {
        return collectPointRepository.findCollectByPayment(paymentRepository.findById(payment));
    }

    @GetMapping("/collectPointtel/{customertel}")
    public Collection<CollectPoint> findCollectByCustomer(@PathVariable String customertel) {
        return collectPointRepository.findCollectByCustomer(customerRepository.findByTel(customertel));
    }
    
    @PostMapping("/collectPoint/{em_id}/{cus_id}/{po_id}/{pay_id}/{point}")
    public CollectPoint newCollectPoint(CollectPoint newCollectPoint,
    @PathVariable long em_id,
    @PathVariable long cus_id,
    @PathVariable long po_id,
    @PathVariable long pay_id,
    @PathVariable Integer point) {
    
    Employee em = employeeRepository.findById(em_id);
    Customer c = customerRepository.findById(cus_id);
    PointPrice po = pointPriceRepository.findById(po_id);
    Payment p = paymentRepository.findById(pay_id);
    LocalDateTime now = LocalDateTime.now();

    newCollectPoint.setEmployee(em);
    newCollectPoint.setCustomer(c);
    newCollectPoint.setPointPrice(po);
    newCollectPoint.setPayment(p);
    newCollectPoint.setPoint(point);
    newCollectPoint.setDate(now);
    
    
    return collectPointRepository.save(newCollectPoint);
    
    }
}