package com.okta.springbootvue.controller.ta;

import com.okta.springbootvue.entity.ta.TitleName;
import com.okta.springbootvue.repository.ta.TitleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class TitleNameController {

    @Autowired
    private final TitleNameRepository titlenameRepository;

    public TitleNameController(TitleNameRepository titlenameRepository) {
        this.titlenameRepository = titlenameRepository;
    }

    @GetMapping("/titlename")
    public Collection<TitleName> TitleNames() {
        return titlenameRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/titlename/{id}")
    public Optional<TitleName> TitleNames(@PathVariable Long id) {
        Optional<TitleName> titlename = titlenameRepository.findById(id);
        return titlename;
    }

}