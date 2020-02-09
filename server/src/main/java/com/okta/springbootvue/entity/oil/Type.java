package com.okta.springbootvue.entity.oil;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name="TYPE")
public class Type {
    @Id    
    @SequenceGenerator(name="type_SEQ",sequenceName="type_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="type_SEQ")  
    @Column(name="ID",unique = true, nullable = true)
    private Long id;

    @NotNull
    @Column(name="TYPE")
    private String type;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Employee> Employee;
}