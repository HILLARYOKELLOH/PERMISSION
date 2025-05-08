package com.jwt.restapi.controller;

import com.jwt.restapi.entity.Customer;
import com.jwt.restapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PreAuthorize("hasPermission('VIEW_CUSTOMER')")
    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @PreAuthorize("hasPermission(null, 'VIEW_CUSTOMER')")
    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }

    @PreAuthorize("hasPermission(null, 'EDIT_CUSTOMER')")
    @PutMapping("/customer/{id}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable("id") Long id) {
        return customerService.updateCustomer(id, customer);
    }

    @PreAuthorize("hasPermission(null, 'CREATE_CUSTOMER')")
    @PostMapping("/customers")
    public Customer addNew(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PreAuthorize("hasPermission(null, 'DELETE_CUSTOMER')")
    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
    }
}
