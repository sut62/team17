package com.okta.springbootvue.entity.joy;

import lombok.*;

import javax.persistence.*;

import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name="BRAND")
public class Brand {
	@Id
	@SequenceGenerator(name="BRAND_SEQ",sequenceName="BRAND_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BRAND_SEQ")
	@Column(name="BRAND_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String name;

	@OneToMany(fetch = FetchType.EAGER)
	// mappedBy  = "rentCustomer"
	private Collection<Product> product;
}