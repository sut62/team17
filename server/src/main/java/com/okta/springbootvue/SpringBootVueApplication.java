package com.okta.springbootvue;

import com.okta.springbootvue.entity.oil.*;
import com.okta.springbootvue.repository.oil.*;
import com.okta.springbootvue.entity.ta.*;
import com.okta.springbootvue.repository.ta.*;
import com.okta.springbootvue.entity.apple.*;
import com.okta.springbootvue.repository.apple.*;
import com.okta.springbootvue.entity.may.*;
import com.okta.springbootvue.repository.may.*;
import com.okta.springbootvue.entity.joy.*;
import com.okta.springbootvue.repository.joy.*;
import com.okta.springbootvue.entity.parn.*;
import com.okta.springbootvue.repository.parn.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import java.util.stream.Stream;
import java.time.LocalDateTime;

@SpringBootApplication  
public class SpringBootVueApplication {  
  
    public static void main(String[] args) {  
      SpringApplication.run(SpringBootVueApplication.class, args);  
    }  

    // Bootstrap some test data into the in-memory database
    @Bean  
    ApplicationRunner init(ProductRepository productRepository, CustomerRepository customerRepository, PaymentRepository paymentRepository,
    PointPriceRepository pointPriceRepository, CollectPointRepository collectPointRepository,
    EmployeeRepository employeeRepository, GenderRepository genderRepository, TypeRepository typeRepository,
    VacancyRepository vacancyRepository, TitleNameRepository titlenameRepository,BrandRepository brandRepository, Type_ProductRepository type_productRepository) {  
        return args -> {  
            Stream.of("Male", "Female").forEach(gender -> {
                Gender g = new Gender();
                g.setGender(gender);
                genderRepository.save(g);
            });

            Stream.of("full-time", "part-time").forEach(type -> {
                Type t = new Type();
                t.setType(type);
                typeRepository.save(t);
            });

	Stream.of(30, 50, 60).forEach(type -> {
                PointPrice p = new PointPrice();
                p.setType(type);
                pointPriceRepository.save(p);
            });

            Stream.of("Manager", "Employee").forEach(vacancy -> {
                Vacancy v = new Vacancy();
                v.setVacancy(vacancy);
                vacancyRepository.save(v);
            });

            Stream.of("oil").forEach(username -> {
            Stream.of("12345678").forEach(password -> {
                Employee e = new Employee();
                LocalDateTime now = LocalDateTime.now();
                e.setDate(now);
                e.setUsername(username);
                e.setPassword(password);
                e.setGender(genderRepository.findById(2));
                e.setType(typeRepository.findById(1));
                e.setVacancy(vacancyRepository.findById(1));
                employeeRepository.save(e);
            });
            });

                    Employee e1 = new Employee();
                    LocalDateTime now = LocalDateTime.now();
                    e1.setDate(now);
                    e1.setUsername("Somsak");
                    e1.setPassword("01234567");
                    e1.setGender(genderRepository.findById(1));
                    e1.setType(typeRepository.findById(2));
                    e1.setVacancy(vacancyRepository.findById(2));
                    employeeRepository.save(e1);

                    Employee e2 = new Employee();
                    LocalDateTime now1 = LocalDateTime.now();
                    e2.setDate(now1);
                    e2.setUsername("Somsri");
                    e2.setPassword("23456789");
                    e2.setGender(genderRepository.findById(2));
                    e2.setType(typeRepository.findById(1));
                    e2.setVacancy(vacancyRepository.findById(2));
                    employeeRepository.save(e2);
 
            Stream.of("L’OREAL", "OLAY", "Maybelline", "AVON","Srichand").forEach(name -> {
				Brand brand= new Brand(); 													// สร้าง Object Customer
				brand.setName(name); 												// set ชื่อ (majorname) ให้ Object ชื่อ Major
				brandRepository.save(brand); 												// บันทึก Objcet ชื่อ Customer
			});
			Stream.of("Lipstick", "Eyeliner", "Cushion", "Blush","Mascara","Powder").forEach(name -> {
				Type_Product type_product= new Type_Product(); 													// สร้าง Object Customer
				type_product.setName(name); 												// set ชื่อ (majorname) ให้ Object ชื่อ Major
				type_productRepository.save(type_product); 												// บันทึก Objcet ชื่อ Customer
			});

            Stream.of("Mr.", "Miss.", "Professor.", "Dr.").forEach(title -> {
				TitleName titlename = new TitleName(); // สร้าง Object Video
				titlename.setTitle(title); // set ชื่อ (name) ให้ Object ชื่อ Video
				titlenameRepository.save(titlename); // บันทึก Objcet ชื่อ Video
            });
            
                Customer r = new Customer();
                r.setName("full");
                r.setLname("free");
                r.setAddress("255 กาฬสินธุ์");
                r.setTel("0915564235");
                r.setDate(LocalDateTime.now());
                r.setTitlename(titlenameRepository.findById(1));
                r.setEmployee(employeeRepository.findById(1));
                r.setGender(genderRepository.findById(1));
                customerRepository.save(r);
                
                Customer r1 = new Customer();
                r1.setName("Part");
                r1.setLname("Yoo");
                r1.setAddress("286 กาฬสินธุ์");
                r1.setTel("0854562365");
                r1.setDate(LocalDateTime.now());
                r1.setTitlename(titlenameRepository.findById(2));
                r1.setEmployee(employeeRepository.findById(1));
                r1.setGender(genderRepository.findById(2));
                customerRepository.save(r1);
                
                Customer r2 = new Customer();
                r2.setName("Time");
                r2.setLname("Eiei");
                r2.setAddress("294 กาฬสินธุ์");
                r2.setTel("0956324568");
                r2.setDate(LocalDateTime.now());
                r2.setTitlename(titlenameRepository.findById(1));
                r2.setEmployee(employeeRepository.findById(1));
                r2.setGender(genderRepository.findById(1));
                customerRepository.save(r2);

                Payment pay1 = new Payment();
                pay1.setPrice((long)300);
                pay1.setMember(customerRepository.findById(1));
                pay1.setEmployee(employeeRepository.findById(2));
                pay1.setDate(LocalDateTime.now());
                paymentRepository.save(pay1);

                Payment pay2 = new Payment();
                pay2.setPrice((long)500);
                pay2.setMember(customerRepository.findById(1));
                pay2.setEmployee(employeeRepository.findById(2));
                pay2.setDate(LocalDateTime.now());
                paymentRepository.save(pay2);

                Payment pay3 = new Payment();
                pay3.setPrice((long)600);
                pay3.setMember(customerRepository.findById(3));
                pay3.setEmployee(employeeRepository.findById(3));
                pay3.setDate(LocalDateTime.now());
                paymentRepository.save(pay3);

            pointPriceRepository.findAll().forEach(System.out::println);
            genderRepository.findAll().forEach(System.out::println);
            typeRepository.findAll().forEach(System.out::println);
            vacancyRepository.findAll().forEach(System.out::println); 
            titlenameRepository.findAll().forEach(System.out::println);
            brandRepository.findAll().forEach(System.out::println); 
            type_productRepository.findAll().forEach(System.out::println);
        };  
    }  
 
}