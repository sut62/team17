package com.okta.springbootvue.Enity.Payment;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.okta.springbootvue.Enity.PaymentType;
import com.okta.springbootvue.Enity.Payment;
import com.okta.springbootvue.Enity.oil.Employee;


@Data
@Entity
@NoArgsConstructor
@Table(name="PAYMENT")
public class Payment {
    @Id
    @SequenceGenerator(name="PAYMENT_SEQ",sequenceName="PAYMENT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PAYMENT_SEQ")
    @Column(name="PAYMENT_ID",unique = true, nullable = true)
    private @NonNull id;

    private @NonNull LocalDateTime date;
   

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PaymentType.class)
    @JoinColumn(name = "PAYMENTTYPE_ID", insertable = true)
    private PaymentType paymenttype;
 
    @OneToMany(fetch = FetchType.EAGER, mappedBy  ="payment")
    @JsonManagedReference //แสดงข้อมูลทุกอย่าง
    private Collection<Payment> payment;

 	
}