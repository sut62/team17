package com.okta.springbootvue;

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
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDateTime;

import com.okta.springbootvue.repository.joy.*;
import com.okta.springbootvue.entity.joy.*;
import com.okta.springbootvue.repository.oil.*;
import com.okta.springbootvue.entity.oil.*;

@DataJpaTest
public class BrandTest {

    private Validator validator;

    @Autowired
    private BrandRepository brandRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6012434_testBrandComplete(){
        Brand brand = new Brand();
        brand.setName("OLAY");

        brand = brandRepository.saveAndFlush(brand);

        Optional<Brand> found = brandRepository.findById(brand.getId());
        assertEquals("OLAY", found.get().getName());
    }

    @Test
    void B6012434_testBrandMustNotิำNull(){
        Brand brand = new Brand();
        brand.setName(null);

        Set<ConstraintViolation<Brand>> result = validator.validate(brand);

    
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Brand> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }
}