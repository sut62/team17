package com.okta.springbootvue.entity.may;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import javax.persistence.*;
import com.okta.springbootvue.entity.ta.Register;
import com.okta.springbootvue.entity.oil.Employee;
import com.okta.springbootvue.entity.parn.Payment;

@Data
@Entity
@NoArgsConstructor
@Table(name="COLLECT_POINT")
public class CollectPoint {
    @Id
    @SequenceGenerator(name="COLLECT_POINT_SEQ",sequenceName="COLLECT_POINT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COLLECT_POINT_SEQ")
    @Column(name="COLLECT_POINT_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull LocalDateTime date;

    private @NonNull Integer point;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Register.class)
    @JoinColumn(name = "REGISTER_ID", insertable = true)
    private Register register;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PointPrice.class)
    @JoinColumn(name = "POINT_PRICE_ID", insertable = true)
    private PointPrice pointPrice;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PAYMENT_ID")
    private Payment payment;
}