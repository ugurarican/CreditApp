package com.ugurarican.creditapp.dataAccess;


import com.ugurarican.creditapp.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    boolean existsByCustomerIdentityNumber(String customerIdentityNumber);
    boolean existsByBirthYear(int birthYear);

}
