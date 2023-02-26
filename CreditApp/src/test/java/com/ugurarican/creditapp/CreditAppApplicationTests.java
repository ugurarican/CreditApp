package com.ugurarican.creditapp;

import com.ugurarican.creditapp.dataAccess.CreditScoreRepository;
import com.ugurarican.creditapp.dataAccess.CustomerRepository;
import com.ugurarican.creditapp.entities.CreditResult;
import com.ugurarican.creditapp.entities.Customer;
import com.ugurarican.creditapp.service.CreditScoreService;
import com.ugurarican.creditapp.service.CreditResultCalculationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

//Kredi basvurusu için hazırlanan bir test sınıfı
@SpringBootTest
class KrediBasvuruApplicationTests {

	@Mock
	private CreditScoreRepository creditScoreRepository;
	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CreditScoreService creditScoreService;

	@InjectMocks
	private CreditResultCalculationService creditResultCalculationService;

	private Customer customer;

	@BeforeEach
	public void setUp(){
		MockitoAnnotations.openMocks(this);
		customer = new Customer(1,"Ahmet", "Yıldırım", "12345678901", new BigDecimal("5000"),1979,1412224646,new BigDecimal("1000"));
		when(customerRepository.existsByCustomerIdentityNumber(customer.getCustomerIdentityNumber())).thenReturn(true);
		when(customerRepository.existsByBirthYear(customer.getBirthYear())).thenReturn(true);
	}

	@Test
	public void testCreditScoreBring(){
		BigDecimal creditScore = creditScoreService.creditScoreBring(customer, new BigDecimal("5000"));
		Assertions.assertEquals(new BigDecimal("1666.67"),creditScore);
	}

	@Test
	public void testCreditResultCalculation1() {
		CreditResult creditResult = creditResultCalculationService.creditResultCalculation(customer, null, new BigDecimal("5000"));
		Assertions.assertEquals(CreditResult.REDDI, creditResult);
	}

	@Test
	public void testCreditResultCalculation2() {
		CreditResult creditResult = creditResultCalculationService.creditResultCalculation(customer, null, new BigDecimal("10000"));
		Assertions.assertEquals(new CreditResult(true, new BigDecimal("20000")), creditResult);
	}

	@Test
	public void testCreditResultCalculation3() {
		CreditResult creditResult = creditResultCalculationService.creditResultCalculation(customer, new BigDecimal("1000"), new BigDecimal("5000"));
		Assertions.assertEquals(new CreditResult(true, new BigDecimal("11000")), creditResult);
	}


}