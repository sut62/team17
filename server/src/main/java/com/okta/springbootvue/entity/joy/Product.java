package com.okta.springbootvue.entity.joy;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;
import com.okta.springbootvue.entity.oil.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PastOrPresent;



@Data
@Entity
@NoArgsConstructor
@Table(name="PRODUCT")
public class Product {

    @Id
    @SequenceGenerator(name="Product_seq",sequenceName="Product_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Product_seq")
    @Column(name = "Product_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @NotNull
    @Positive
    @Column(name="QUANTITY")
    private  Integer quantity;

    @NotNull
    @Positive
    @Column(name="PRICE")
    private  Integer price;

    @NotNull
    @PastOrPresent
    @Column(name="DATE")
    private  LocalDateTime date;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "Employee_ID", insertable = true)
    private Employee employee;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Brand.class)
    @JoinColumn(name = "Brand_ID", insertable = true)
    private Brand brand;
    
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Type_Product.class)
    @JoinColumn(name = "Type_Product_ID", insertable = true)
    private Type_Product type_product;

    
}