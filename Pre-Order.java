package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Collection;

import com.cpe.backend.entity.Employee;
import com.cpe.backend.entity.Add_Product;
import com.cpe.backend.entity.Title_Name;
import com.cpe.backend.entity.Number;

@Data
@Entity
@NoArgsConstructor

@Table(name="PRE-ORDER")
public class Pre-order {

    @Id
    @SequenceGenerator(name="pre-order_seq",sequenceName="pre-order_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pre-order_seq")
    @Column(name = "PRE-ORDER_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="CUS_NAME")
    private @NonNull String cus_name;

    @Column(name="DATE")
    private @NonNull LocalDateTime Date;

    @Column(name="TEL")
    private @NonNull String tel;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employee;
	
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "ADD_PRODUCT_ID", insertable = true)
    private Add_Product product;
}