package com.okta.springbootvue.entity.apple;

import lombok.*;
import java.util.*;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="QUANTITY")
public class Quantity {
    @Id
    @SequenceGenerator(name="QUANTITY_SEQ",sequenceName="quantity_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="quantity_seq")
    @Column(name="QUANTITY_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull int quantity;

}