package com.okta.springbootvue.entity.parn;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

/*import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table; */

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Data
@Entity
@NoArgsConstructor
@Table(name="PAYMENT")
public class Payment {
    @Id
    @SequenceGenerator(name="PAYMENT_SEQ",sequenceName="PAYMENT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PAYMENT_SEQ")
    
    @Column(name="PAYMENT_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull Long price;
    private @NonNull LocalDateTime date;
   

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PaymentType.class)
    @JoinColumn(name = "PAYMENT_TYPE_ID", insertable = true)
    private PaymentType type;


 	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Register.class)
    @JoinColumn(name = "REGISTER_ID", insertable = true)
    private Register member;
 
    @OneToMany(fetch = FetchType.EAGER, mappedBy  ="payment")
    @JsonManagedReference //แสดงข้อมูลทุกอย่าง
    private Collection<Product> product;

}