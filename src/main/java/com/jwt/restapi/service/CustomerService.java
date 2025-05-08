package com.jwt.restapi.service;

import com.jwt.restapi.entity.Customer;
import com.jwt.restapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get customer by ID
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    // Create a new customer
    public Customer createCustomer(Customer customer) {
        // createdBy and createdDate will be handled by auditing
        return customerRepository.save(customer);
    }

    // Update an existing customer
    public Customer updateCustomer(Long id, Customer updatedData) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));

        // Apply updated fields
        existingCustomer.setFirstName(updatedData.getFirstName());
        existingCustomer.setMiddleName(updatedData.getMiddleName());
        existingCustomer.setLastName(updatedData.getLastName());
        existingCustomer.setMobile(updatedData.getMobile());
        existingCustomer.setEmail(updatedData.getEmail());
        existingCustomer.setLine1(updatedData.getLine1());
        existingCustomer.setLine2(updatedData.getLine2());
        existingCustomer.setCity(updatedData.getCity());
        existingCustomer.setProvince(updatedData.getProvince());
        existingCustomer.setCountry(updatedData.getCountry());

        // lastModifiedBy and lastModifiedDate will be handled by auditing
        return customerRepository.save(existingCustomer);
    }

    // Delete a customer by ID
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
