package com.okta.springbootvue.entity.ta;

import lombok.*;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

import javax.validation.constraints.*;

import com.okta.springbootvue.entity.oil.Employee;
import com.okta.springbootvue.entity.oil.Gender;
import com.okta.springbootvue.entity.ta.TitleName;

@Data
@Entity
@NoArgsConstructor
@Table(name="CUSTOMER",
    uniqueConstraints = @UniqueConstraint(columnNames = {"TEL"})
)
public class Customer {

    @Id
    @SequenceGenerator(name="customer_seq",sequenceName="customer_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="customer_seq")
    @Column(name = "CUSTOMER_ID", unique = true, nullable = true)
    private Long id;

    @NotNull
    @Column(name="DATE")
    private LocalDateTime date;

    @NotNull
    @Size(min = 2 ,max = 30)
    @Column(name="NAME")
    private String name;

    @NotNull
    @Size(min = 2 ,max = 30)
    @Column(name="LASTNAME")
    private String lname;

    @NotNull
    @Column(name="ADDRESS")
    private String address;

    @NotNull
    @Pattern(regexp = "\\d{10}")
    @Column(name="TEL")
    private String tel;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TitleName.class)
    @JoinColumn(name = "TITLENAME_ID", insertable = true)
    private TitleName titlename;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EM_ID", insertable = true)
    private Employee employee;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "GENDER_ID", insertable = true)
    private Gender gender;
}
