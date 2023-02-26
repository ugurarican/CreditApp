package com.ugurarican.creditapp.service;

import com.ugurarican.creditapp.dataAccess.CustomerRepository;
import com.ugurarican.creditapp.entities.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreditResultCalculationService {

    private final CreditScoreService creditScoreService;
    private final CustomerRepository customerRepository;


    public CreditResultCalculationService(CreditScoreService creditScoreService, CustomerRepository customerRepository){
        this.creditScoreService = creditScoreService;
        this.customerRepository = customerRepository;
    }

    public CreditResult creditResultCalculation(Customer customer,BigDecimal guarantee,BigDecimal monthlyIncome ){

        //Kullanıcı bilgilerinin kayıtlı olup olmamasını kontrol edeceğiz
        boolean isUserExist = customerRepository.existsByCustomerIdentityNumber(customer.getCustomerIdentityNumber())
                && customerRepository.existsByBirthYear(customer.getBirthYear());
        if (!isUserExist){
            return CreditResult.REDDI;
        }

        BigDecimal  creditScore = creditScoreService.creditScoreBring(customer,monthlyIncome);
        BigDecimal customerMonthlyIncome = customer.getMonthlyIncome();

        if (creditScore.compareTo(new BigDecimal(500)) < 0){
            return CreditResult.REDDI;
        // Kredi skoru 500'ün altında ise kullanıcı reddedilir.
        }


        else if (creditScore.compareTo(new BigDecimal(500)) >= 0 && creditScore.compareTo(new BigDecimal(1000)) <= 0
                && customerMonthlyIncome.compareTo(new BigDecimal(5000)) < 0) {
            BigDecimal creditLimit = new BigDecimal(10000);

            if (guarantee != null) {
                creditLimit = creditLimit.add(guarantee.multiply(new BigDecimal("0.1")));
            }
            return new CreditResult(true, creditLimit);
            // Kredi skoru 500 puan ile 1000 puan arasında ise ve aylık geliri 5000 altında ise  10000 tl limit tanımlanır.
        }


        else if (creditScore.compareTo(new BigDecimal(1000))<= 0 && customerMonthlyIncome.compareTo(new BigDecimal(5000)) <0) {
            BigDecimal creditLimit = new BigDecimal(10000);

            if (guarantee != null){
                creditLimit = creditLimit.add(guarantee.multiply(new BigDecimal("0.1")));
            }
            return new CreditResult(true, creditLimit);
            // Kredi skoru 500 puan ile 1000 puan arasında ise ve aylık geliri 5000 altında ise  10000 tl limit tanımlanır.
        }


        else if (creditScore.compareTo(new BigDecimal(1000)) <= 0 && customerMonthlyIncome.compareTo(new BigDecimal(10000)) <0 ) {
            BigDecimal creditLimit = new BigDecimal(20000);

            if (guarantee != null){
                creditLimit = creditLimit.add(guarantee.multiply(new BigDecimal("0.2")));
            }
            return new CreditResult(true, creditLimit);
            // Kredi skoru 500 ile 1000 arasında ve aylık gelir 5000-10000 arasında ise 20000 tl limit tanımlanır
        }


        else if(creditScore.compareTo(new BigDecimal(1000)) <= 0){
            BigDecimal creditLimit = customerMonthlyIncome.multiply(new BigDecimal(4));
            if (guarantee != null){
                creditLimit = creditLimit.add(guarantee.multiply(new BigDecimal("0.25")));
            }
            return new CreditResult(true,creditLimit);
            //aylık gelir 10000 tl nin üstünde ise aylık gelir bilgisi*kredi limit çarpanı/2 kadar limit tanımlanır
        }


        else {
            BigDecimal creditLimit = customerMonthlyIncome.multiply(new BigDecimal(4));
            if (guarantee != null){
                creditLimit = creditLimit.add(guarantee.multiply(new BigDecimal("0.5")));

            }
            return new CreditResult(true,creditLimit);
        }
    }
}
