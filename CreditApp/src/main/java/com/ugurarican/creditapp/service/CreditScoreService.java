package com.ugurarican.creditapp.service;

import com.ugurarican.creditapp.dataAccess.CreditScoreRepository;
import com.ugurarican.creditapp.entities.Customer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreditScoreService {

    private final CreditScoreRepository creditScoreRepository;



    public CreditScoreService(CreditScoreRepository creditScoreRepository) {
        this.creditScoreRepository = creditScoreRepository;
    }

    public BigDecimal creditScoreBring(Customer customer, BigDecimal monthlyIncome) {

        BigDecimal creditScore = customer.getMonthlyIncome().divide(new BigDecimal(10)) // aylık maaşın onda biri
                .subtract(customer.getDebt().multiply(new BigDecimal(3)));  // toplam borcun üç katının çıkarılması

//Kredi skoru min 0 olmalıdır.

        if(creditScore.compareTo(BigDecimal.ZERO) <0){
            creditScore = BigDecimal.ZERO;
        }


        return creditScore;
    }
}
