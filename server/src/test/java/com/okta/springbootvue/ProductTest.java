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
public class ProductTest{

    private Validator validator;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private Type_ProductRepository type_productRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6012434_ProductdSaveSuccess() {
        Employee employee = employeeRepository.findById(2);
        Brand brand = brandRepository.findById(1);
        Type_Product type_product = type_productRepository.findById(1);
        LocalDateTime now = LocalDateTime.now(); 
        Product product = new Product();
        product.setQuantity(1);
        product.setPrice(12);
        product = productRepository.saveAndFlush(product);
        product.setEmployee(employee);
        product.setBrand(brand);
        product.setType_product(type_product);
        product.setDate(now);

        product = productRepository.saveAndFlush(product);

    Optional<Product> found = productRepository.findById(product.getId());
    assertEquals((int)1, (int)found.get().getQuantity());
    assertEquals((int)12, (int)found.get().getPrice());
  }

    @Test
    void B6012434_ProductQuantityNullCase(){
        Employee employee = employeeRepository.findById(2);
        Brand brand = brandRepository.findById(1);
        Type_Product type_product = type_productRepository.findById(1);
        LocalDateTime now = LocalDateTime.now(); 
        Product product = new Product();
        product.setQuantity(null);
        product.setPrice(12000);
        product.setEmployee(employee);
        product.setBrand(brand);
        product.setType_product(type_product);
        product.setDate(now);

        Set<ConstraintViolation<Product>> result = validator.validate(product);

        assertEquals(1, result.size());

        ConstraintViolation<Product> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("quantity", v.getPropertyPath().toString());

    }

    
    @Test
    void B6012434_testProductQuatityNotPositiveCase(){
        Employee employee = employeeRepository.findById(2);
        Brand brand = brandRepository.findById(1);
        Type_Product type_product = type_productRepository.findById(1);
        LocalDateTime now = LocalDateTime.now(); 
        Product product = new Product();
        product.setQuantity(-50);
        product.setPrice(12000);
        product.setEmployee(employee);
        product.setBrand(brand);
        product.setType_product(type_product);
        product.setDate(now);

        Set<ConstraintViolation<Product>> result = validator.validate(product);

        assertEquals(1, result.size());

        ConstraintViolation<Product> v = result.iterator().next();
        assertEquals("must be greater than 0", v.getMessage());
        assertEquals("quantity", v.getPropertyPath().toString());

    }

    @Test
    void B6012434_testProductQuatityNotPastOrPresentCase(){
        Employee employee = employeeRepository.findById(2);
        Brand brand = brandRepository.findById(1);
        Type_Product type_product = type_productRepository.findById(1);
        LocalDateTime now = LocalDateTime.now(); 
        Product product = new Product();
        product.setQuantity(50);
        product.setPrice(12000);
        product.setEmployee(employee);
        product.setBrand(brand);
        product.setType_product(type_product);
        product.setDate(LocalDateTime.now().plusDays(1));

        Set<ConstraintViolation<Product>> result = validator.validate(product);

        assertEquals(1, result.size());

        ConstraintViolation<Product> v = result.iterator().next();
        assertEquals("must be a date in the past or in the present", v.getMessage());
        assertEquals("date", v.getPropertyPath().toString());

    }

    
}