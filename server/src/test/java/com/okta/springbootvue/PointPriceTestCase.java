package com.okta.springbootvue;

import com.okta.springbootvue.entity.may.*;
import com.okta.springbootvue.repository.may.*;
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


@DataJpaTest
public class PointPriceTestCase {

    private Validator validator;

    @Autowired
    private CollectPointRepository collectPointRepository;
    @Autowired
    private PointPriceRepository pointPriceRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void b6000042_testPointPriceSaveComplete(){
        PointPrice p = new PointPrice();
        p.setType(30);
        p = pointPriceRepository.saveAndFlush(p);

        Optional<PointPrice> found = pointPriceRepository.findById(p.getId());
        assertEquals(p.getType(), found.get().getType());
    }

    @Test
    void b6000042_testTypeMustNotBeNull(){
        PointPrice p = new PointPrice();
        p.setType(null);

        Set<ConstraintViolation<PointPrice>> result = validator.validate(p);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PointPrice> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("type", v.getPropertyPath().toString());
    }

    @Test
    void b6000042_testTypeLessThan30(){
        PointPrice p = new PointPrice();
        p.setType(29);

        Set<ConstraintViolation<PointPrice>> result = validator.validate(p);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PointPrice> v = result.iterator().next();
        assertEquals("must be greater than or equal to 30", v.getMessage());
        assertEquals("type", v.getPropertyPath().toString());
    }

    @Test
    void b6000042_testTypeGreaterThan60(){
        PointPrice p = new PointPrice();
        p.setType(61);

        Set<ConstraintViolation<PointPrice>> result = validator.validate(p);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<PointPrice> v = result.iterator().next();
        assertEquals("must be less than or equal to 60", v.getMessage());
        assertEquals("type", v.getPropertyPath().toString());
    }
    
}