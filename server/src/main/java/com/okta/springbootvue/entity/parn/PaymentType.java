package com.okta.springbootvue.entity.parn;

import lombok.*;
import javax.persistence.*;
//import java.time.LocalDateTime;

import java.util.Collection;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;

/*import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;*/

@Data
@Entity
@NoArgsConstructor
@Table(name="PAYMENT_TYPE")
public class PaymentType {

    @Id
    @SequenceGenerator(name="PAYMENT_TYPE_SEQ",sequenceName="PAYMENT_TYPE_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PAYMENT_TYPE_SEQ")  
    @Column(name = "PAYMENT_TYPE_ID", unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String name;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Payment> payment;

}