package com.okta.springbootvue;
import com.okta.springbootvue.entity.joy.*;
import com.okta.springbootvue.repository.joy.*;
import com.okta.springbootvue.entity.oil.*;
import com.okta.springbootvue.repository.oil.*;
import com.okta.springbootvue.entity.ta.*;
import com.okta.springbootvue.repository.ta.*;
import com.okta.springbootvue.entity.apple.*;
import com.okta.springbootvue.repository.apple.*;

import java.time.LocalDateTime;

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
public class Pre_OrderTest {

    private Validator validator;
    @Autowired
    private  Pre_OrderRepository pre_orderRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private Type_ProductRepository type_productRepository;
    @Autowired
    private TitleNameRepository titlenameRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test

  @Test
    void B6014551_testPre_OrderTelMustBeNumberOnly(){

        TitleName titlename = titlenameRepository.findById(1);
        Brand brand = brandRepository.findById(2);
        Type_Product type_product = type_productRepository.findById(3);
        Employee employee = employeeRepository.findById(2);
        LocalDateTime now = LocalDateTime.now();
    
        Pre_Order pre_order = new Pre_Order();
        pre_order.setCus_name("Chawee");
        pre_order.setTel("09562x9763");
        pre_order.setQuantity(2);
        pre_order.setEmployee(employee);
        pre_order.setBrand(brand);
        pre_order.setType_product(type_product);
        pre_order.setDate(now);

        Set<ConstraintViolation<Pre_Order>> result = validator.validate(pre_order);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pre_Order> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());
    }

    @Test
    void B6014551_testPre_OrderNotBeNullCase(){

        TitleName titlename = titlenameRepository.findById(1);
        Brand brand = brandRepository.findById(2);
        Type_Product type_product = type_productRepository.findById(3);
        Employee employee = employeeRepository.findById(2);
        LocalDateTime now = LocalDateTime.now();
    
        Pre_Order pre_order = new Pre_Order();
        pre_order.setCus_name(null);
        pre_order.setTel("0951239763");
        pre_order.setQuantity(2);
        pre_order.setEmployee(employee);
        pre_order.setBrand(brand);
        pre_order.setType_product(type_product);
        pre_order.setDate(now);

        Set<ConstraintViolation<Pre_Order>> result = validator.validate(pre_order);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pre_Order> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("cus_name", v.getPropertyPath().toString());
    }

    @Test
    void B6014551_testQuantityGreaterThan0(){

        TitleName titlename = titlenameRepository.findById(1);
        Brand brand = brandRepository.findById(2);
        Type_Product type_product = type_productRepository.findById(3);
        Employee employee = employeeRepository.findById(2);
        LocalDateTime now = LocalDateTime.now();
    
        Pre_Order pre_order = new Pre_Order();
        pre_order.setCus_name("Chawee");
        pre_order.setTel("0951239763");
        pre_order.setQuantity(0);
        pre_order.setEmployee(employee);
        pre_order.setBrand(brand);
        pre_order.setType_product(type_product);
        pre_order.setDate(now);

        Set<ConstraintViolation<Pre_Order>> result = validator.validate(pre_order);

        //ต้องมี 1 error เท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Pre_Order> v = result.iterator().next();
        assertEquals("must be greater than 0",v.getMessage());
        assertEquals("quantity", v.getPropertyPath().toString());
    }

    @Test
    void B6014551_testSaveComplete(){

        Pre_Order pre_order = new Pre_Order();

        TitleName titlename = titlenameRepository.findById(1);
        Brand brand = brandRepository.findById(2);
        Type_Product type_product = type_productRepository.findById(3);
        Employee employee = employeeRepository.findById(2);
        LocalDateTime now = LocalDateTime.now();

        pre_order.setCus_name("Chawee");
        pre_order.setTel("0957777773");
        pre_order.setQuantity(2);
        pre_order.setTitlename(titlename);
        pre_order.setEmployee(employee);
        pre_order.setBrand(brand);
        pre_order.setType_product(type_product);
        pre_order.setDate(now);

        pre_order = pre_orderRepository.saveAndFlush(pre_order);

        Optional<Pre_Order> found = pre_orderRepository.findById(pre_order.getId());

        assertEquals("Chawee", found.get().getCus_name());
        assertEquals("0957777773", found.get().getTel());
        assertEquals(2, found.get().getQuantity());
        assertEquals(titlename, found.get().getTitlename());
        assertEquals(employee, found.get().getEmployee());
        assertEquals(brand, found.get().getBrand());
        assertEquals(type_product, found.get().getType_product());
        assertEquals(now, found.get().getDate());
    }

  

}


















