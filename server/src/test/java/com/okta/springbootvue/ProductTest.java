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
        product.setEmployee(employee);
        product.setBrand(brand);
        product.setType_product(type_product);
        product.setDate(now);

        product = productRepository.saveAndFlush(product);

    Optional<Product> found = productRepository.findById(product.getId());
    assertEquals((int)1, (int)found.get().getQuantity());
    assertEquals((int)12, (int)found.get().getPrice());
    assertEquals(employee, found.get().getEmployee());
    assertEquals(brand, found.get().getBrand());
    assertEquals(type_product, found.get().getType_product());
    assertEquals(now, found.get().getDate());
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
    void B6012434_ProductPriceNullCase(){
        Employee employee = employeeRepository.findById(2);
        Brand brand = brandRepository.findById(1);
        Type_Product type_product = type_productRepository.findById(1);
        LocalDateTime now = LocalDateTime.now(); 
        Product product = new Product();
        product.setQuantity(12);
        product.setPrice(null);
        product.setEmployee(employee);
        product.setBrand(brand);
        product.setType_product(type_product);
        product.setDate(now);

        Set<ConstraintViolation<Product>> result = validator.validate(product);

        assertEquals(1, result.size());

        ConstraintViolation<Product> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("price", v.getPropertyPath().toString());

    }

    @Test
    void B6012434_ProductEmployeeNullCase(){
        Brand brand = brandRepository.findById(1);
        Type_Product type_product = type_productRepository.findById(1);
        LocalDateTime now = LocalDateTime.now(); 
        Product product = new Product();
        product.setQuantity(12);
        product.setPrice(12000);
        product.setEmployee(null);
        product.setBrand(brand);
        product.setType_product(type_product);
        product.setDate(now);

        Set<ConstraintViolation<Product>> result = validator.validate(product);

        assertEquals(1, result.size());

        ConstraintViolation<Product> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("employee", v.getPropertyPath().toString());

    }

    @Test
    void B6012434_ProductType_ProductNullCase(){
        Employee employee = employeeRepository.findById(2);
        Type_Product type_product = type_productRepository.findById(1);
        LocalDateTime now = LocalDateTime.now(); 
        Product product = new Product();
        product.setQuantity(12);
        product.setPrice(12500);
        product.setEmployee(employee);
        product.setBrand(null);
        product.setType_product(type_product);
        product.setDate(now);

        Set<ConstraintViolation<Product>> result = validator.validate(product);

        assertEquals(1, result.size());

        ConstraintViolation<Product> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("brand", v.getPropertyPath().toString());

    }
    
    @Test
    void B6012434_ProductBrandNullCase(){
        Employee employee = employeeRepository.findById(2);
        Brand brand = brandRepository.findById(1);
        LocalDateTime now = LocalDateTime.now(); 
        Product product = new Product();
        product.setQuantity(12);
        product.setPrice(12500);
        product.setEmployee(employee);
        product.setBrand(brand);
        product.setType_product(null);
        product.setDate(now);

        Set<ConstraintViolation<Product>> result = validator.validate(product);

        assertEquals(1, result.size());

        ConstraintViolation<Product> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("type_product", v.getPropertyPath().toString());

    }

    @Test
    void B6012434_ProductDateNullCase(){
        Employee employee = employeeRepository.findById(2);
        Brand brand = brandRepository.findById(1);
        Type_Product type_product = type_productRepository.findById(1);
        LocalDateTime now = LocalDateTime.now(); 
        Product product = new Product();
        product.setQuantity(12);
        product.setPrice(12500);
        product.setEmployee(employee);
        product.setBrand(brand);
        product.setType_product(type_product);
        product.setDate(null);

        Set<ConstraintViolation<Product>> result = validator.validate(product);

        assertEquals(1, result.size());

        ConstraintViolation<Product> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("date", v.getPropertyPath().toString());

    }
    
    @Test
    void B6012434_testProductQuatityPositiveCase(){
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
    void B6012434_testProductPricePositiveCase(){
        Employee employee = employeeRepository.findById(2);
        Brand brand = brandRepository.findById(1);
        Type_Product type_product = type_productRepository.findById(1);
        LocalDateTime now = LocalDateTime.now(); 
        Product product = new Product();
        product.setQuantity(50);
        product.setPrice(-12000);
        product.setEmployee(employee);
        product.setBrand(brand);
        product.setType_product(type_product);
        product.setDate(now);

        Set<ConstraintViolation<Product>> result = validator.validate(product);

        assertEquals(1, result.size());

        ConstraintViolation<Product> v = result.iterator().next();
        assertEquals("must be greater than 0", v.getMessage());
        assertEquals("price", v.getPropertyPath().toString());

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