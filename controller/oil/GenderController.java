package com.okta.springbootvue.controller.oil;

import com.okta.springbootvue.entity.oil.Gender;
import com.okta.springbootvue.repository.oil.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class GenderController {

    @Autowired
    private final GenderRepository genderRepository;

    public GenderController(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @GetMapping("/Gender")
    public Collection<Gender> Genders() {
        return genderRepository.findAll().stream().collect(Collectors.toList());
    }

}