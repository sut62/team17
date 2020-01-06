package com.okta.springbootvue.entity.parn;

import lombok.*;
import javax.persistence.*;
//import java.time.LocalDateTime;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference; 
import java.util.Collection;

/*import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;*/

@Data
@Entity
@Setter @Getter
@NoArgsConstructor
@Table(name="ADD_PRODUCT")
public class Add_Product{
    @Id
    @SequenceGenerator(name="ADD_PRODUCT_SEQ",sequenceName = "ADD_PRODUCT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ADD_PRODUCT_SEQ")
    @Column(name="ADD_PRODUCT_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="add_Product")
    @JsonManagedReference 
    private Collection<Product> product;
}
