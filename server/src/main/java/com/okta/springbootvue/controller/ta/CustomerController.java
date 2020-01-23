package com.okta.springbootvue.controller.ta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.okta.springbootvue.entity.ta.*;
import com.okta.springbootvue.entity.oil.*;
import com.okta.springbootvue.repository.ta.*;
import com.okta.springbootvue.repository.oil.*;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CustomerController {

    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TitleNameRepository titlenameRepository;
    @Autowired
    private GenderRepository genderRepository;

    CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customer")
    public Collection<Customer> Customers() {
        return customerRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/customer/{tel}")
    public Collection<Customer> getByTel(@PathVariable String tel) {
        return customerRepository.findByTel(tel);
    }

    @PostMapping("/customer/{name}/{lname}/{address}/{tel}/{employee_id}/{titlename_id}/{gender_id}")
    public Customer newCustomer(Customer newCustomer,
    @PathVariable String name,
    @PathVariable String lname,
    @PathVariable String address,
    @PathVariable String tel,
    @PathVariable long employee_id,
    @PathVariable long titlename_id,
    @PathVariable long gender_id) {
 
    Employee employee = employeeRepository.findById(employee_id);
    TitleName titlename = titlenameRepository.findById(titlename_id);
    Gender gender = genderRepository.findById(gender_id);
    LocalDateTime now = LocalDateTime.now();

    newCustomer.setDate(now);
    newCustomer.setEmployee(employee);
    newCustomer.setTitlename(titlename);
    newCustomer.setName(name);
    newCustomer.setLname(lname);
    newCustomer.setGender(gender);
    newCustomer.setAddress(address);
    newCustomer.setTel(tel);
    
    return customerRepository.save(newCustomer); //บันทึก Objcet ชื่อ Customer
    
    }
}