package com.okta.springbootvue.entity.parn;

import lombok.*;
import javax.persistence.*;
//import java.time.LocalDateTime;
import java.util.Collection;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonManagedReference;

/*import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;*/

@Data
@Entity
@NoArgsConstructor
@Table(name="EMPLOYEE")
public class Employee {

    @Id
    @SequenceGenerator(name="EMPLOYEE_SEQ",sequenceName="EMPLOYEE_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EMPLOYEE_SEQ")  
    @Column(name = "EMPLOYEE_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="PASSWORD")
    private @NonNull String password; 

    @Column(name="USERNAME")
    private @NonNull String username;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Payment> payment;

	public void setName(String name) {
	}
}
