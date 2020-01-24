package com.okta.springbootvue;

import com.okta.springbootvue.entity.ta.*;
import com.okta.springbootvue.repository.ta.*;
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
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class CustomerTest {
    private Validator validator;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TitleNameRepository titlenameRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private GenderRepository genderRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test 
    void b6002008_testInsertNameforCustomerOK() {
        Customer customer = new Customer();
        LocalDateTime now = LocalDateTime.now();
        Employee employee = employeeRepository.findById(2);
        TitleName titlename = titlenameRepository.findById(2);
        Gender gender = genderRepository.findById(2);
        customer.setDate(now);
        customer.setEmployee(employee);
        customer.setTitlename(titlename);
        customer.setName("Caca");
        customer.setLname("Kaka");
        customer.setGender(gender);
        customer.setAddress("BA108 Suranaree");
        customer.setTel("0873018064");

        customer = customerRepository.saveAndFlush(customer);
        
        Optional<Customer> found = customerRepository.findById(customer.getId());
        assertEquals(now, found.get().getDate());
        assertEquals(employee, found.get().getEmployee());
        assertEquals(titlename, found.get().getTitlename());
        assertEquals("Caca", found.get().getName());
        assertEquals("Kaka", found.get().getLname());
        assertEquals(gender, found.get().getGender());
        assertEquals("BA108 Suranaree", found.get().getAddress());
        assertEquals("0873018064", found.get().getTel());
    }

     @Test // ที่อยู่ห้ามว่าง
     void b6002008_testAddressforCustomerMustNotBeNull() {
        Customer customer = new Customer();
        LocalDateTime now = LocalDateTime.now();
        customer.setDate(now);
        customer.setName("Caca");
        customer.setLname("Kaka");
        customer.setAddress(null);
        customer.setTel("0873018064");

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("address", v.getPropertyPath().toString());
    }
    
    @Test // ห้ามมีตัวอักษรใน Tel และต้องมีตัวเลขเท่ากับ 10
    void b6002008_testCustomerTelMustHaveNumber() {
        Customer customer = new Customer();
        LocalDateTime now = LocalDateTime.now();
        customer.setDate(now);
        customer.setName("Caca");
        customer.setLname("Kaka");
        customer.setAddress("BA108 Suranaree");
        customer.setTel("C873018064");

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());
    }

    @Test  //ชื่อห้ามน้อยกว่า 2 ตัว
    void b6002008_testCustomerNameMustSizeLessThan2() { 
        Customer customer = new Customer();
        LocalDateTime now = LocalDateTime.now();
        customer.setDate(now);
        customer.setName("C");
        customer.setLname("Kaka");
        customer.setAddress("บ้านสุรนารี");
        customer.setTel("0873018064");

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("size must be between 2 and 30", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test  //นามสกุลห้ามน้อยกว่า 2 ตัว
    void b6002008_testCustomerLnameMustSizeLessThan2() { 
        Customer customer = new Customer();
        LocalDateTime now = LocalDateTime.now();
        customer.setDate(now);
        customer.setName("Caca");
        customer.setLname("K");
        customer.setAddress("บ้านสุรนารี");
        customer.setTel("0873018064");

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("size must be between 2 and 30", v.getMessage());
        assertEquals("lname", v.getPropertyPath().toString());
    }

    @Test  //เบอร์โทรห้ามซ้ำ
    void b6002008_testTelMustBeUnique() { 
        Customer c1 = new Customer();
        LocalDateTime now = LocalDateTime.now();
        c1.setDate(now);
        c1.setName("Caca");
        c1.setLname("Kaka");
        c1.setAddress("บ้านสุรนารี");
        c1.setTel("0873018064");

         customerRepository.saveAndFlush(c1);

        // คาดหวังว่า DataIntegrityViolationException จะถูก throw
        assertThrows(DataIntegrityViolationException.class, () -> {
            // สร้าง customer object ตัวที่ 2
        Customer c2 = new Customer();
        c2.setDate(now);
        c2.setName("Caca");
        c2.setLname("Kaka");
        c2.setAddress("บ้านสุรนารี");
        c2.setTel("0873018064");

         customerRepository.saveAndFlush(c2);
        });
    }

}