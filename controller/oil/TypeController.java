package com.okta.springbootvue.controller.oil;

import com.okta.springbootvue.entity.oil.Type;
import com.okta.springbootvue.repository.oil.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class TypeController {

    @Autowired
    private final TypeRepository typeRepository;

    public TypeController(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @GetMapping("/Type")
    public Collection<Type> Types() {
        return typeRepository.findAll().stream().collect(Collectors.toList());
    }

}