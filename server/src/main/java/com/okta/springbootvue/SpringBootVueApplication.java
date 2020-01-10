package com.okta.springbootvue;

import com.okta.springbootvue.entity.oil.*;
import com.okta.springbootvue.repository.oil.*;
import com.okta.springbootvue.entity.ta.*;
import com.okta.springbootvue.repository.ta.*;
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
    ApplicationRunner init(EmployeeRepository employeeRepository, GenderRepository genderRepository, TypeRepository typeRepository, VacancyRepository vacancyRepository,
    TitleNameRepository titlenameRepository) {  
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

            Stream.of("Manager", "Employee").forEach(vacancy -> {
                Vacancy v = new Vacancy();
                v.setVacancy(vacancy);
                vacancyRepository.save(v);
            });

            Stream.of("Oil Pattanan").forEach(username -> {
            Stream.of("2222").forEach(password -> {
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

            Stream.of("Mr.", "Miss.", "Professor.", "Dr.").forEach(title -> {
				TitleName titlename = new TitleName(); // สร้าง Object Video
				titlename.setTitle(title); // set ชื่อ (name) ให้ Object ชื่อ Video
				titlenameRepository.save(titlename); // บันทึก Objcet ชื่อ Video
			});

            genderRepository.findAll().forEach(System.out::println);
            typeRepository.findAll().forEach(System.out::println);
            vacancyRepository.findAll().forEach(System.out::println); 
            titlenameRepository.findAll().forEach(System.out::println);
        };  
    }  
 
}