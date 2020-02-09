package com.okta.springbootvue.entity.may;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@Table(name="POINT_PRICE")
public class PointPrice {
	@Id
	@SequenceGenerator(name="POINT_PRICE_SEQ",sequenceName="POINT_PRICE_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POINT_PRICE_SEQ")
	@Column(name="POINT_PRICE_ID",unique = true, nullable = true)
	private @NonNull Long id;

	@NotNull
	@Max(60)
	@Min(30)
	private Integer type;

	@OneToMany(fetch = FetchType.EAGER)
	private Collection<CollectPoint> collectpoint;
}