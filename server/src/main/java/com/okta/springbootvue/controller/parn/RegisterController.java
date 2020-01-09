package com.okta.springbootvue.controller.parn;

import  com.okta.springbootvue.entity.parn.Register;
import com.okta.springbootvue.repository.parn.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class RegisterController {

    @Autowired
    private final RegisterRepository registerRepository;

    public RegisterController(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @GetMapping("/register")
    public Collection<Register> Register() {
        return registerRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/register/{id}")
    public Register getRegisterId(@PathVariable("id") Long id) {
         return registerRepository.findById(id).get();
    }
   

} 