package com.okta.springbootvue.entity.ta;

import javax.validation.constraints.*;
import lombok.*;
import javax.persistence.*;
import java.util.Collection;


@Data
@Entity
@NoArgsConstructor
@Table(name="TITLENAME")
public class TitleName {
	@Id
	@SequenceGenerator(name="titlename_seq",sequenceName="titlename_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="titlename_seq")
	@Column(name="TITLENAME_ID",unique = true, nullable = true)
	private Long id;

	@NotNull
	@Column(name="TITLENAME")
	private  String title;

	@OneToMany(fetch = FetchType.EAGER)
	private Collection<Customer> customer;
}