package com.ugurarican.creditapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private int id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;

    @Column(name = "IdentityNumber")
    @Size(min = 11, max = 11, message = "Kimlik numarası 11 haneden oluşmalıdır.")
    @Digits(integer = 11, fraction = 0,message = "Kimlik numrası 11 rakamdan oluşmalıdır." )
    @Pattern(regexp = "\\d{11}", message = "Kimlik numarası 11 rakamdan oluşmalıdır.")
    private String customerIdentityNumber;

    @Column(name = "monthlyIncome")
    private BigDecimal  monthlyIncome;
    @Column(name = "birthYear")
    private int birthYear;

    @Column(name = "customer_phone_number")
    private double phoneNumber;

    @Column(name = "customer_debt")
    private BigDecimal debt; // borçlar


}
