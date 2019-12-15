package com.okta.backend.entity.ta;

import lombok.*;
import javax.persistence.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.okta.backend.entity.ta.Gender;
import com.okta.backend.entity.ta.TitleName;

@Data
@Entity
@NoArgsConstructor
@Table(name="REGISTER")
public class Register {

    @Id
    @SequenceGenerator(name="register_seq",sequenceName="register_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="register_seq")
    @Column(name = "REGISTER_ID", unique = true, nullable = true)
    private @NonNull Long id;
	
    @Column(name="NAME")
    private @NonNull String name;

    @Column(name="LASTNAME")
    private @NonNull String lname;

    @Column(name="ADDRESS")
    private @NonNull String address;

    @Column(name="TEL")
    private @NonNull Long tel;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TitleName.class)
    @JoinColumn(name = "TITLENAME_ID", insertable = true)
    private TitleName titlename;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "GENDER_ID", insertable = true)
    private Gender gender;
}