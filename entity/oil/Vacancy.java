package com.okta.springbootvue.entity.oil;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name="VACANCY")
public class Vacancy {
    @Id    
    @SequenceGenerator(name="vacancy_SEQ",sequenceName="vacancy_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="vacancy_SEQ")  
    @Column(name="ID",unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="VACANCY")
    private @NonNull String vacancy;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Employee> Employee;
}