package com.ugurarican.creditapp.business.customer;


import com.ugurarican.creditapp.model.DataResult;
import com.ugurarican.creditapp.model.Result;
import com.ugurarican.creditapp.entities.Customer;

import java.util.List;

public interface CustomerService {

    Result add(Customer customer); // yeni kullanıcı ekleme

    Result update(Customer customer);  // Mevcut kullanıcı güncelleme

    Result delete(int id);  // Mevcut kullanıcı silme

    DataResult<List<Customer>> getAll(); // Kullanıcıları listeleme



}
