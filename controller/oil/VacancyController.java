package com.okta.springbootvue.controller.oil;

import com.okta.springbootvue.entity.oil.Vacancy;
import com.okta.springbootvue.repository.oil.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class VacancyController {

    @Autowired
    private final VacancyRepository vacancyRepository;

    public VacancyController(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    @GetMapping("/Vacancy")
    public Collection<Vacancy> Vacancys() {
        return vacancyRepository.findAll().stream().collect(Collectors.toList());
    }

}