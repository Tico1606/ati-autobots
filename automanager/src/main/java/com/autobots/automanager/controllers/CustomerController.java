package com.autobots.automanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entities.Customer;
import com.autobots.automanager.repositories.CustomerRepository;
import com.autobots.automanager.model.CustomerSelector;
import com.autobots.automanager.model.CustomerUpdater;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private CustomerSelector selector;

  @Autowired
  private CustomerUpdater updater;

  @GetMapping
  public ResponseEntity<List<Customer>> getAllCustomers() {
    List<Customer> customers = customerRepository.findAll();
    if (customers.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(customers);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
    List<Customer> customers = customerRepository.findAll();
    Customer customer = selector.select(customers, id);
    if (customer == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.ok(customer);
  }

  @PostMapping
  public ResponseEntity<Customer> createCustomer(@RequestBody Customer newCustomer) {
    Customer saved = customerRepository.save(newCustomer);
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
    List<Customer> customers = customerRepository.findAll();
    Customer existingCustomer = selector.select(customers, id);
    if (existingCustomer == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    updater.update(existingCustomer, updatedCustomer);
    customerRepository.save(existingCustomer);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
    List<Customer> customers = customerRepository.findAll();
    Customer customer = selector.select(customers, id);
    if (customer == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    customerRepository.delete(customer);
    return ResponseEntity.ok().build();
  }
}
