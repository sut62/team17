package com.okta.springbootvue.entity.apple;

import lombok.*;
import java.util.*;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="NUMBER")
public class Number {
    @Id
    @SequenceGenerator(name="NUMBER_SEQ",sequenceName="number_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="number_seq")
    @Column(name="NUMBER_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull int num;

}