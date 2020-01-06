package com.okta.springbootvue.entity.oil;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name="GENDER")
public class Gender {
    @Id    
    @SequenceGenerator(name="gender_SEQ",sequenceName="gender_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gender_SEQ")  
    @Column(name="ID",unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="GENDER")
    private @NonNull String gender;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Employee> Employee;
}