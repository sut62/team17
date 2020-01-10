package com.okta.springbootvue;

import com.okta.springbootvue.entity.oil.*;
import com.okta.springbootvue.repository.oil.*;
import com.okta.springbootvue.entity.may.*;
import com.okta.springbootvue.repository.may.*;
import com.okta.springbootvue.entity.joy.*;
import com.okta.springbootvue.repository.joy.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import java.util.stream.Stream;
import java.time.LocalDateTime;

@SpringBootApplication  
public class SpringBootVueApplication {  
  
    public static void main(String[] args) {  
      SpringApplication.run(SpringBootVueApplication.class, args)  
    }  

    // Bootstrap some test data into the in-memory database
    @Bean  
    ApplicationRunner init(PointPriceRepository pointPriceRepository, CollectPointRepository collectPointRepository, EmployeeRepository employeeRepository, GenderRepository genderRepository, TypeRepository typeRepository, VacancyRepository vacancyRepository,BrandRepository brandRepository, Type_ProductRepository type_productRepository) {  
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
            Stream.of("2").forEach(password -> {
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
           Stream.of("L’OREAL", "OLAY", "Maybelline", "AVON","Srichand").forEach(name -> {
	Brand brand= new Brand(); 													
	brand.setName(name); 												
	brandRepository.save(brand); 												
           });

           Stream.of("Lipstick", "Eyeliner", "Cushion", "Blush","Mascara","Powder").forEach(name -> {
	Type_Product type_product= new Type_Product(); 													
	type_product.setName(name); 												
	type_productRepository.save(type_product); 												
           });

            pointPriceRepository.findAll().forEach(System.out::println);
            genderRepository.findAll().forEach(System.out::println);
            typeRepository.findAll().forEach(System.out::println);
            vacancyRepository.findAll().forEach(System.out::println);
            brandRepository.findAll().forEach(System.out::println); 
            type_productRepository.findAll().forEach(System.out::println);
            
        };  
    }  
 
}