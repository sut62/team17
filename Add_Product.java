package com.cpe.backend.Add_Product.entity;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name="Add_PRODUCT")
public class Add_Product {

    @Id
    @SequenceGenerator(name="Add_Product_seq",sequenceName="Add_Product_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Add_Product_seq")
    @Column(name = "Add_Product_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="QUANTITY")
    private @NonNull Integer quantity;

    @Column(name="PRICE")
    private @NonNull Integer price;

    @Column(name="DATE")
    private @NonNull LocalDateTime date;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "Employee_ID", insertable = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Brand.class)
    @JoinColumn(name = "Brand_ID", insertable = true)
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Type_Product.class)
    @JoinColumn(name = "Type_Product_ID", insertable = true)
    private Type_Product type_product;

    
}