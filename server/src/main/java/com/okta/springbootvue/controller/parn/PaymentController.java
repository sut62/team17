package com.okta.springbootvue.controller.parn;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.okta.springbootvue.model.BodyPayment;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import java.time.LocalDateTime;

import com.okta.springbootvue.entity.parn.Payment;
import com.okta.springbootvue.entity.oil.Employee;
import com.okta.springbootvue.entity.parn.PaymentType;
import com.okta.springbootvue.entity.ta.Register;
import com.okta.springbootvue.entity.joy.Product;

import com.okta.springbootvue.repository.parn.PaymentRepository;
import com.okta.springbootvue.repository.oil.EmployeeRepository;
import com.okta.springbootvue.repository.parn.PaymentTypeRepository;
import com.okta.springbootvue.repository.ta.RegisterRepository;
import com.okta.springbootvue.repository.joy.ProductRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class PaymentController {
    @Autowired
    private final PaymentRepository paymentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RegisterRepository registerRepository;

    public PaymentController( PaymentRepository paymentRepository, EmployeeRepository employeeRepository, PaymentTypeRepository paymentTypeRepository, 
    	ProductRepository productRepository, RegisterRepository registerRepository) {
        this.paymentRepository = paymentRepository;
        this.employeeRepository = employeeRepository;
        this.paymentTypeRepository = paymentTypeRepository;
        this.productRepository = productRepository;
        this.registerRepository = registerRepository;
    }

    /*@GetMapping("/payment")
    public Collection<Payment> Payment() {
        return paymentRepository.findAll().stream().collect(Collectors.toList());
    } */

   /*@PostMapping("/paymented")
    public Payment index(@RequestBody BodyPayment bodyPayment){//newPayment(Payment newPayment,
        Payment pay = new Payment();
        Register member = registerRepository.findById(bodyPayment.getRegisterid()).get();
        Product pd = productRepository.findById(bodyPayment.getProductid()).get();
        pay.setRegister(member);
        pay.setProduct(pd);
        pay.setPrice(bodyPayment.getSumprice());
        LocalDateTime now = LocalDateTime.now(); 

        int i=0, j=0;
        for(Long addId : bodyPayment.getAdd_Productid()) {
          
         //   System.out.println(addId);
         //   System.out.println();
            Add_Product ap = add_ProductRepository.findById(addId).get();
            Product p = new Product();
            p.setPayment(pay);
            p.setAdd_product(ap);

           for(Long qty : bodyPayment.getQuantity()){

            if(i == j){
                p.setProductQty(qty);
             }
             j ++;
         }
            j = 0;
            i ++;
            productRepository.save(p);
        }
        return paymentRepository.save(pay);
     }*/
     @GetMapping("/payment")
     public Collection<Payment> Payment() {
         return paymentRepository.findAll().stream().collect(Collectors.toList());
        }
    }

       /* int i = 0;
        int j = 0;

        for (Long addId : bodyPayment.getAdd_ProductId()) {
         
             Add_Product ap = add_ProductRepository.findById(addId).get();
             Product prod = new Product();
            prod.setPayment(pay);
            prod.setAdd_Product(ap);
            
             for (final Long qty : bodyPayment.getQuantity()) {
                 
                 if(i == j){
                    prod.setProductQty(qty);
                 }
                 j ++;
             }
             j = 0;
             i ++;
             productRepository.save(prod);
         }
    return paymentRepository.save(pay); //บันทึก Objcet ชื่อ Booking
        }
        
    @GetMapping("/payment")
    public Collection<Payment> Payment() {
        return paymentRepository.findAll().stream().collect(Collectors.toList());
    }
}         
    /*@PathVariable long employee_id,
    @PathVariable long type_id,
    @PathVariable long product_id,
    @PathVariable long add_id) */

    /*Employee em = employeeRepository.findById(employee_id);
    PaymentType type = paymentTypeRepository.findById(type_id);
    Product pro = productRepository.findById(product_id);
    Add_Product add = add_ProductRepository.findById(add_id); */
   

    //newPayment.setEmployee(em);
    //newPayment.setType(type);
    //newPayment.setProduct(pro);
    //newPayment.setAdd_Product(add);
 

    