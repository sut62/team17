package com.okta.springbootvue.controller.may;

import com.okta.springbootvue.entity.may.PointPrice;
import com.okta.springbootvue.repository.may.PointPriceRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PointPriceController {

    @Autowired
    private final PointPriceRepository pointPriceRepository;

    public PointPriceController(PointPriceRepository pointPriceRepository) {
        this.pointPriceRepository = pointPriceRepository;
    }

    @GetMapping("/pointPrice")
    public Collection<PointPrice> PointPrices() {
        return pointPriceRepository.findAll().stream().collect(Collectors.toList());
    }

}