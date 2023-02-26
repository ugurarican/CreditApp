package com.ugurarican.creditapp.controllers;

import com.ugurarican.creditapp.entities.CreditResult;
import com.ugurarican.creditapp.entities.Customer;
import com.ugurarican.creditapp.service.CreditResultCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/credit")
@CrossOrigin
public class CreditApplicationController {

    private final CreditResultCalculationService creditResultCalculationService;

@Autowired
    public CreditApplicationController(CreditResultCalculationService creditResultCalculationService){
        this.creditResultCalculationService = creditResultCalculationService;
    }

    @GetMapping("/creditResultCalculation")
    public CreditResult creditResultCalculation(Customer customer, @RequestParam BigDecimal guarantee, BigDecimal monthlyIncome){
        return creditResultCalculationService.creditResultCalculation(customer,guarantee,monthlyIncome);
    }
}
