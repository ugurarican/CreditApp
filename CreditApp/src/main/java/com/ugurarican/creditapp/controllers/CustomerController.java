package com.ugurarican.creditapp.controllers;

import com.ugurarican.creditapp.model.DataResult;
import com.ugurarican.creditapp.model.Result;
import com.ugurarican.creditapp.entities.Customer;
import com.ugurarican.creditapp.business.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {

private CustomerService customerService;
@Autowired
    public CustomerController(CustomerService customerService){
    this.customerService = customerService;
}


@PostMapping("/add")
public Result add(@RequestBody Customer customer){
    return this.customerService.add(customer);
}

@PutMapping("/update")
    public Result update(@RequestBody Customer customer){
    return this.customerService.update(customer);
}

@DeleteMapping("/delete")
public Result delete(@RequestParam int id){
    return this.customerService.delete(id);
}

@GetMapping("/getAll")
    public DataResult<List<Customer>> getAll() {
    return this.customerService.getAll();
}


}
