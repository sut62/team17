package com.okta.springbootvue.entity.parn;

import lombok.*;
import javax.persistence.*;
//import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import java.util.Collection;

/*
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.FetchType; */


@Entity  
@Data  
@NoArgsConstructor  
@Table(name = "PRODUCT")
public class Product {  
      
  @Id 
  @SequenceGenerator(name="PRODUCT_seq",sequenceName="PRODUCT_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PRODUCT_seq") 
  @Column(name = "PRODUCT_ID", unique = true, nullable = true)
  
  @Getter@Setter
  private @NonNull Long id;  
 // private @NonNull String name; 
 // private @NonNull Integer count; 
  private @NonNull Long ProductQty;

  @Getter @Setter
  @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL,targetEntity = Payment.class)
  @JoinColumn(name = "PAYMENT")
  @JsonIgnore
  private Payment payment; 

  @Getter@Setter
  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Add_Product.class)
  @JoinColumn(name = "ADD_PRODUCT_ID", insertable = true)
  @JsonIgnore // แสดงเฉพาะ PrimaryKey
  private Add_Product add;

}