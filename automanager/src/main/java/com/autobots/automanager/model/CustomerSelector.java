package com.autobots.automanager.model;

import java.util.List;
import org.springframework.stereotype.Component;
import com.autobots.automanager.entities.Customer;

@Component
public class CustomerSelector {
  public Customer select(List<Customer> customers, long id) {
    return customers.stream()
      .filter(customer -> customer.getId() == id)
      .findFirst()
      .orElse(null);
  }
}
