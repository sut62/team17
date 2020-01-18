package com.okta.springbootvue.entity.ta;

import lombok.*;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

import com.okta.springbootvue.entity.oil.Employee;
import com.okta.springbootvue.entity.oil.Gender;
import com.okta.springbootvue.entity.ta.TitleName;

@Data
@Entity
@NoArgsConstructor
@Table(name="CUSTOMER")
public class Customer {

    @Id
    @SequenceGenerator(name="customer_seq",sequenceName="customer_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="customer_seq")
    @Column(name = "CUSTOMER_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="DATE")
    private @NonNull LocalDateTime date;
	
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
    @JoinColumn(name = "EM_ID", insertable = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "GENDER_ID", insertable = true)
    private Gender gender;
}
