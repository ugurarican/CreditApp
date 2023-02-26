package com.ugurarican.creditapp.business.customer;

import com.ugurarican.creditapp.model.*;
import com.ugurarican.creditapp.dataAccess.CustomerRepository;
import com.ugurarican.creditapp.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerManager implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerManager(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public Result add(Customer customer) {

        String identityNumber = customer.getCustomerIdentityNumber();
        if(identityNumber != null && identityNumber.length() != 11){
            return new ErrorResult("Kimlik numarası 11 rakamdan oluşmalıdır.");
        }
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        int birthYear = customer.getBirthYear();
        if (birthYear >= (currentYear - 18) || String.valueOf(birthYear).length() != 4){
            return new ErrorResult("18 Yaşından küçük kullanıcılar eklenemezler");
        }

        String phoneNumber = String.valueOf(customer.getPhoneNumber());
        if(phoneNumber.length() != 11){
            return new ErrorResult("Telefon numarası 10 rakamdan oluşmalıdır.");
        }


        this.customerRepository.save(customer);
        return new SuccessResult("Yeni bir kullanici eklendi");
    }

    @Override
    public Result update(Customer customer) {
        this.customerRepository.save(customer);
        return new SuccessResult("Kullanicilar güncellendi");
    }

    @Override
    public Result delete(int id) {
        this.customerRepository.deleteById(id);
        return new SuccessResult("Mevcut kullanıcı silindi");
    }

    @Override
    public DataResult<List<Customer>> getAll() {
        return new SuccessDataResult<List<Customer>>(this.customerRepository.findAll());

    }
}
