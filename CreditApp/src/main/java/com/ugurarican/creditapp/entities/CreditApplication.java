package com.ugurarican.creditapp.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")

public class CreditApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private int id;
    @Column(name = "customer_identity_number")
    private String customerTcIdNo;
    @Column(name = "customer_first_name")
    private String customerFirstName;
    @Column(name = "customer_last_name")
    private String customerLastName;
    @Column(name = "customer_income")
    private int     customerIncome;  //Müsteri geliri
    @Column(name = "customer_phone_number")
    private int     customerPhoneNumber;
    @Column(name = "customer_birth_year")
    private int     customerBirthYear;
    @Column(name = "customer_guarantee")
    private String  customerGuarantee; // teminat
    @Column(name = "customer_credit_score")
    private String  customerCreditScore;
}
