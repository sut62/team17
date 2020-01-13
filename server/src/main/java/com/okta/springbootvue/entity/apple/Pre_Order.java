package com.okta.springbootvue.entity.apple;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import javax.persistence.*;

import com.okta.springbootvue.entity.oil.Employee;
import com.okta.springbootvue.entity.ta.TitleName;
import com.okta.springbootvue.entity.joy.Brand;
import com.okta.springbootvue.entity.joy.Type_Product;


@Data
@Entity
@NoArgsConstructor

@Table(name="PRE_ORDER")
public class Pre_Order {

    @Id
    @SequenceGenerator(name="PRE_ORDER_SEQ",sequenceName="pre_order_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pre_order_seq")
    @Column(name = "PRE_ORDER_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="CUS_NAME")
    private @NonNull String cus_name;

    @Column(name="DATE")
    private @NonNull LocalDateTime Date;

    @Column(name="TEL")
    private @NonNull String tel;

    @Column(name="QUANTITY")
    private @NonNull int quantity;


    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TitleName.class)
    @JoinColumn(name = "TITLENAME_ID", insertable = true)
    private TitleName titlename;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Brand.class)
    @JoinColumn(name = "BRAND_ID", insertable = true)
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Type_Product.class)
    @JoinColumn(name = "TYPE_PRODUCT_ID", insertable = true)
    private Type_Product type_product;  
    
}