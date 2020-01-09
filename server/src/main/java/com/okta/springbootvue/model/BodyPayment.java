package com.okta.springbootvue.model;
import lombok.*;

// model for request body พักค่า
@Data
public  class BodyPayment {
    private Long registerId;
    private Long sumprice;
    private Long[] addProductId;
    private Long[] quantity;
}