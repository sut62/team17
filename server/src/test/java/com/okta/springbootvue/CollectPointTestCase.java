package com.okta.springbootvue;

import com.okta.springbootvue.entity.may.*;
import com.okta.springbootvue.repository.may.*;
import com.okta.springbootvue.repository.parn.PaymentRepository;
import com.okta.springbootvue.repository.ta.CustomerRepository;
import com.okta.springbootvue.repository.oil.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class CollectPointTestCase {

    private Validator validator;

    @Autowired
    private CollectPointRepository collectPointRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PointPriceRepository pointPriceRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void b6000042_testCollectPointSaveComplete() {
        CollectPoint col = new CollectPoint();
        col.setDate(LocalDateTime.now());
        col.setPoint(16);
        col.setEmployee(employeeRepository.findById(2));
        col.setCustomer(customerRepository.findById(1));
        col.setPointPrice(pointPriceRepository.findById(1));
        col.setPayment(paymentRepository.findById(2));

        col = collectPointRepository.saveAndFlush(col);

        Optional<CollectPoint> found = collectPointRepository.findById(col.getId());
        assertEquals(paymentRepository.findById(2), found.get().getPayment());
    }

    @Test
    void b6000042_testPointMustNotBeNull() {
        CollectPoint col = new CollectPoint();
        col.setDate(LocalDateTime.now());
        col.setPoint(null);
        col.setEmployee(employeeRepository.findById(2));
        col.setCustomer(customerRepository.findById(1));
        col.setPointPrice(pointPriceRepository.findById(1));
        col.setPayment(paymentRepository.findById(1));

        Set<ConstraintViolation<CollectPoint>> result = validator.validate(col);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CollectPoint> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("point", v.getPropertyPath().toString());
    }
    @Test
    void b6000042_testDateMustBePastOrPresent(){
        CollectPoint col = new CollectPoint();
        col.setDate(LocalDateTime.now().plusDays(1));
        col.setPoint(10);
        col.setEmployee(employeeRepository.findById(2));
        col.setCustomer(customerRepository.findById(1));
        col.setPointPrice(pointPriceRepository.findById(1));
        col.setPayment(paymentRepository.findById(1));

        Set<ConstraintViolation<CollectPoint>> result = validator.validate(col);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CollectPoint> v = result.iterator().next();
        assertEquals("must be a date in the past or in the present", v.getMessage());
        assertEquals("date", v.getPropertyPath().toString());
    }
    @Test
    void b6000042_testPointMustBeGreaterThanOrEqualsTo1() {
        CollectPoint col = new CollectPoint();
        col.setDate(LocalDateTime.now());
        col.setPoint(0);
        col.setEmployee(employeeRepository.findById(2));
        col.setCustomer(customerRepository.findById(1));
        col.setPointPrice(pointPriceRepository.findById(1));
        col.setPayment(paymentRepository.findById(1));

        Set<ConstraintViolation<CollectPoint>> result = validator.validate(col);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CollectPoint> v = result.iterator().next();
        assertEquals("must be greater than or equal to 1", v.getMessage());
        assertEquals("point", v.getPropertyPath().toString());
    }

    @Test
    void b6000042_testPaymentMustBeUnique() {
        // สร้าง person object
        CollectPoint col = new CollectPoint();
        col.setDate(LocalDateTime.now());
        col.setPoint(10);
        col.setEmployee(employeeRepository.findById(2));
        col.setCustomer(customerRepository.findById(1));
        col.setPointPrice(pointPriceRepository.findById(1));
        col.setPayment(paymentRepository.findById(1));
        collectPointRepository.saveAndFlush(col);

        // คาดหวังว่า DataIntegrityViolationException จะถูก throw
        assertThrows(DataIntegrityViolationException.class, () -> {
            // สร้าง person object ตัวที่ 2
            CollectPoint col1 = new CollectPoint();
            col1.setDate(LocalDateTime.now());
            col1.setPoint(10);
            col1.setEmployee(employeeRepository.findById(2));
            col1.setCustomer(customerRepository.findById(1));
            col1.setPointPrice(pointPriceRepository.findById(1));
            col1.setPayment(paymentRepository.findById(1));
            collectPointRepository.saveAndFlush(col1);
        });
    }

}