package com.okta.springbootvue.entity.oil;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.okta.springbootvue.entity.oil.*;
import com.okta.springbootvue.entity.joy.*;
import com.okta.springbootvue.entity.may.CollectPoint;

@Data
@Entity
@NoArgsConstructor
@Table(name="EMPLOYEE",
    uniqueConstraints = @UniqueConstraint(columnNames = {"USERNAME"})
)
public class Employee {

    @Id
    @SequenceGenerator(name="employee_seq",sequenceName="employee_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employee_seq") 
    @Column(name = "ID", unique = true, nullable = true)
    private Long id;

    @NotNull
    @Column(name="DATE")
    private LocalDateTime date;
    
    @NotNull
    @Column(name="USERNAME")
    private String username;

    @NotNull
    @Size(min=8, max=16)
    @Column(name="PASSWORD")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "GENDER_ID", insertable = true)
    private Gender gender;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Type.class)
    @JoinColumn(name = "TYPE_ID", insertable = true)
    private Type type;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Vacancy.class)
    @JoinColumn(name = "VACANCY_ID", insertable = true)
    private Vacancy vacancy;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Add_Product> add_product;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<CollectPoint> collectpoint;

}