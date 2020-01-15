package com.okta.springbootvue.controller.oil;

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

import com.okta.springbootvue.entity.oil.*;
import com.okta.springbootvue.repository.oil.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController //แปลงdataเป็นjson file
public class EmployeeController {
    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private VacancyRepository vacancyRepository;
    

    EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @GetMapping("/Employee/{username}/{password}")
    public Employee getPatientByFirstName(@PathVariable("username") String username, @PathVariable("password") String password) {
        return employeeRepository.findPatientByEmployee(username,password);
    }
    @GetMapping("/Employee")
    public Collection<Employee> Employees() {
        return employeeRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/employee/{id}")
    public Employee getEmployeeId(@PathVariable("id") Long id) {
         return employeeRepository.findById(id).get();
    }

    @PostMapping("/Employee/{username}/{password}/{gender_id}/{type_id}/{vacancy_id}")
    public Employee newEmployee(Employee newEmployee,
    @PathVariable String username,
    @PathVariable String password,
    @PathVariable long gender_id,
    @PathVariable long type_id,
    @PathVariable long vacancy_id) {
    
    Gender gender = genderRepository.findById(gender_id);
    Type type = typeRepository.findById(type_id);
    Vacancy vacancy = vacancyRepository.findById(vacancy_id);
    LocalDateTime now = LocalDateTime.now();
    
    newEmployee.setDate(now);
    newEmployee.setUsername(username);
    newEmployee.setPassword(password);
    newEmployee.setGender(gender);
    newEmployee.setType(type);
    newEmployee.setVacancy(vacancy);

    return employeeRepository.save(newEmployee);
    
    }
}