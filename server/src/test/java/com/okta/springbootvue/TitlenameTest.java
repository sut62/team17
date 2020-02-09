package com.okta.springbootvue;

import com.okta.springbootvue.entity.ta.*;
import com.okta.springbootvue.repository.ta.*;

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
public class TitlenameTest {
    private Validator validator;

    @Autowired
    private TitleNameRepository titlenameRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6002008_testTitlenameCustomerIsOK() {
        TitleName titlename = new TitleName();
        titlename.setTitle("Miss.");

        titlename = titlenameRepository.saveAndFlush(titlename);

        Optional<TitleName> found = titlenameRepository.findById(titlename.getId());
        assertEquals("Miss.", found.get().getTitle());
    }

    @Test
    void B6005795_testTitlenameMustNotBeNull() {
        TitleName titlename = new TitleName();
        titlename.setTitle(null);

        Set<ConstraintViolation<TitleName>> result = validator.validate(titlename);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<TitleName> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("title", v.getPropertyPath().toString());

    }
}