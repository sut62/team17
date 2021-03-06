package com.okta.springbootvue.entity.joy;

import lombok.*;

import javax.persistence.*;

import java.util.Collection;
import javax.validation.constraints.NotNull;


@Data
@Entity
@NoArgsConstructor
@Table(name="TYPE_PRODUCT")
public class Type_Product {
    @Id
    @SequenceGenerator(name="TYPE_PRODUCT_SEQ",sequenceName="TYPE_PRODUCT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TYPE_PRODUCT_SEQ")
    @Column(name="TYPE_PRODUCT_ID",unique = true, nullable = true)
    private Long id;
    @NotNull
    private  String name;

    @OneToMany(fetch = FetchType.EAGER)
    // mappedBy  = "createdBy"
    private Collection<Product> product;
}
