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

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class EmployeeTests {

    private Validator validator;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private VacancyRepository vacancyRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void b6018221_testEmployeeComplete() {
        Employee employee = new Employee();
        LocalDateTime now = LocalDateTime.now();
        Gender gender = genderRepository.findById(2);
        Type type = typeRepository.findById(2);
        Vacancy vacancy = vacancyRepository.findById(2);

        employee.setDate(now);
        employee.setUsername("ta");
        employee.setPassword("12345678");
        employee.setGender(gender);
        employee.setType(type);
        employee.setVacancy(vacancy);

        employee = employeeRepository.saveAndFlush(employee);

        Optional<Employee> found = employeeRepository.findById(employee.getId());
        assertEquals(now, found.get().getDate());
        assertEquals("ta", found.get().getUsername());
        assertEquals("12345678", found.get().getPassword());
        assertEquals(gender, found.get().getGender());
        assertEquals(type, found.get().getType());
        assertEquals(vacancy, found.get().getVacancy());
    }

    @Test
    void b6018221_testUsernameMustNotBeNull() {
        Employee employee = new Employee();
        LocalDateTime now = LocalDateTime.now();
        Gender gender = genderRepository.findById(2);
        Type type = typeRepository.findById(2);
        Vacancy vacancy = vacancyRepository.findById(2);

        employee.setDate(now);
        employee.setUsername(null);
        employee.setPassword("12345678");
        employee.setGender(gender);
        employee.setType(type);
        employee.setVacancy(vacancy);

        Set<ConstraintViolation<Employee>> result = validator.validate(employee);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("username", v.getPropertyPath().toString());
    }

    @Test
    void b6018221_testPasswordLessThan8Alphabets() {
        Employee employee = new Employee();
        LocalDateTime now = LocalDateTime.now();
        Gender gender = genderRepository.findById(2);
        Type type = typeRepository.findById(2);
        Vacancy vacancy = vacancyRepository.findById(2);

        employee.setDate(now);
        employee.setUsername("ta");
        employee.setPassword("1234567"); // 7 alphabets
        employee.setGender(gender);
        employee.setType(type);
        employee.setVacancy(vacancy);

        Set<ConstraintViolation<Employee>> result = validator.validate(employee);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("size must be between 8 and 16", v.getMessage());
        assertEquals("password", v.getPropertyPath().toString());
    }

    @Test
    void b6018221_testPasswordMoreThan16Alphabets() {
        Employee employee = new Employee();
        LocalDateTime now = LocalDateTime.now();
        Gender gender = genderRepository.findById(2);
        Type type = typeRepository.findById(2);
        Vacancy vacancy = vacancyRepository.findById(2);

        employee.setDate(now);
        employee.setUsername("ta");
        employee.setPassword("12345678901234567"); // 17 alphabets
        employee.setGender(gender);
        employee.setType(type);
        employee.setVacancy(vacancy);

        Set<ConstraintViolation<Employee>> result = validator.validate(employee);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("size must be between 8 and 16", v.getMessage());
        assertEquals("password", v.getPropertyPath().toString());
    }

    @Test
    void b6018221_testUsernameMustBeUnique() {
        // สร้าง employee object
        Employee e1 = new Employee();
        LocalDateTime now = LocalDateTime.now();
        Gender g1 = genderRepository.findById(2);
        Type t1 = typeRepository.findById(2);
        Vacancy v1 = vacancyRepository.findById(2);

        e1.setDate(now);
        e1.setUsername("ta");
        e1.setPassword("12345678");
        e1.setGender(g1);
        e1.setType(t1);
        e1.setVacancy(v1);

        employeeRepository.saveAndFlush(e1);

        // คาดหวังว่า DataIntegrityViolationException จะถูก throw
        assertThrows(DataIntegrityViolationException.class, () -> {
            // สร้าง employee object ตัวที่ 2
            Employee e2 = new Employee();
            Gender g2 = genderRepository.findById(1);
            Type t2 = typeRepository.findById(1);
            Vacancy v2 = vacancyRepository.findById(2);

            e2.setDate(now);
            e2.setUsername("ta");
            e2.setPassword("12345678");
            e2.setGender(g2);
            e2.setType(t2);
            e2.setVacancy(v2);

            employeeRepository.saveAndFlush(e2);
        });
    }

}


















