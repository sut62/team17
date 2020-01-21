package com.okta.springbootvue.entity.may;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import javax.persistence.*;
import com.okta.springbootvue.entity.ta.Customer;
import com.okta.springbootvue.entity.oil.Employee;
import com.okta.springbootvue.entity.parn.Payment;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;;

@Data
@Entity
@NoArgsConstructor
@Table(name="COLLECT_POINT",
    uniqueConstraints = @UniqueConstraint(columnNames = {"PAYMENT_ID"})
 )
public class CollectPoint {
    @Id
    @SequenceGenerator(name="COLLECT_POINT_SEQ",sequenceName="COLLECT_POINT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COLLECT_POINT_SEQ")
    @Column(name="COLLECT_POINT_ID",unique = true, nullable = true)
    private Long id;
    
    @NotNull
    @PastOrPresent
    private LocalDateTime date;

    @Min(1)
    @NotNull
    private Integer point;
    
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "REGISTER_ID", insertable = true)
    private Customer customer;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employee;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PointPrice.class)
    @JoinColumn(name = "POINT_PRICE_ID", insertable = true)
    private PointPrice pointPrice;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PAYMENT_ID")
    private Payment payment;
}