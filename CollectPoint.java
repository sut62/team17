package com.okta.springbootvue.Enity.Pointmay;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.okta.springbootvue.Enity.Customer;
import com.okta.springbootvue.Enity.Pointmay.PointPrice;
import com.okta.springbootvue.Enity.oil.Employee;
import com.okta.springbootvue.Enity.Payment;

@Data
@Entity
@NoArgsConstructor
@Table(name="COLLECT_POINT")
public class CollectPoint {
    @Id
    @SequenceGenerator(name="COLLECT_POINT_SEQ",sequenceName="COLLECT_POINT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COLLECT_POINT_SEQ")
    @Column(name="COLLECT_POINT_ID",unique = true, nullable = true)
    private @NonNull id;

    private @NonNull LocalDateTime date;

    private @NonNull Integer point;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID", insertable = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PointPrice.class)
    @JoinColumn(name = "POINTPRICE_ID", insertable = true)
    private PointPrice pointPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment")
    @JsonManagedReference
    private Payment payment;
}