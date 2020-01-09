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
public class EmployeeController {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employee")
    public Collection<Employee> Employee() {
        return employeeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeId(@PathVariable("id") Long id) {
         return employeeRepository.findById(id).get();
    }


}


