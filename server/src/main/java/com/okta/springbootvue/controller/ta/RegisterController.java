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

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class RegisterController {

    @Autowired
    private final RegisterRepository registerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TitleNameRepository titlenameRepository;
    @Autowired
    private GenderRepository genderRepository;

    RegisterController(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @GetMapping("/register")
    public Collection<Register> Registers() {
        return registerRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/register/{name}/{lname}/{address}/{tel}/{employee_id}/{titlename_id}/{gender_id}")
    public Register newRegister(Register newRegister,
    @PathVariable String name,
    @PathVariable String lname,
    @PathVariable String address,
    @PathVariable Long tel,
    @PathVariable long employee_id,
    @PathVariable long titlename_id,
    @PathVariable long gender_id) {
 
    Employee employee = employeeRepository.findById(employee_id);
    TitleName titlename = titlenameRepository.findById(titlename_id);
    Gender gender = genderRepository.findById(gender_id);

    newRegister.setEmployee(employee);
    newRegister.setTitlename(titlename);
    newRegister.setName(name);
    newRegister.setLname(lname);
    newRegister.setAddress(address);
    newRegister.setTel(tel);
    newRegister.setGender(gender);

    return registerRepository.save(newRegister); //บันทึก Objcet ชื่อ Register
    
    }
}