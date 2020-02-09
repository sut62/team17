package com.okta.springbootvue;

import com.okta.springbootvue.entity.oil.*;
import com.okta.springbootvue.repository.oil.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class VacancyTests {

    private Validator validator;

    @Autowired
    private VacancyRepository vacancyRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void b6018221_testVacancyComplete(){
        Vacancy vacancy = new Vacancy();
        vacancy.setVacancy("Employee");

        vacancy = vacancyRepository.saveAndFlush(vacancy);

        Optional<Vacancy> found = vacancyRepository.findById(vacancy.getId());
        assertEquals("Employee", found.get().getVacancy());
    }

    @Test
    void b6018221_testVacancyMustNotBeNull(){
        Vacancy vacancy = new Vacancy();
        vacancy.setVacancy(null);

        Set<ConstraintViolation<Vacancy>> result = validator.validate(vacancy);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Vacancy> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("vacancy", v.getPropertyPath().toString());
    }
}