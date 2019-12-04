package com.okta.springbootvue.enity.Customer;

import lombok.*;
import javax.persistence.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.cpe.backend.entity.Gender;
import com.cpe.backend.entity.Title_Name;
import com.cpe.backend.entity.Employee;

@Data
@Entity
@NoArgsConstructor
@Table(name="CUSTOMER")
public class Customer {
    @Id
    @SequenceGenerator(name="CUSTOMER_SEQ",sequenceName="CUSTOMER_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CUSTOMER_SEQ")
    @Column(name="CUSTOMER_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="NAME")
    private @NonNull String name;

    @Column(name="L_NAME")
    private @NonNull String l_name;

    @Column(name="ADDRESS")
    private @NonNull String address;

    @Column(name="TEL")
    private @NonNull Long tel;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Title_Name.class)
    @JoinColumn(name = "TITLE_NAME_ID", insertable = true)
    private Title_Name titlename;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "GENDER_ID", insertable = true)
    private Gender gender;

}