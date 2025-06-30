package com.autobots.automanager;

import java.util.Calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;

import com.autobots.automanager.entities.Customer;
import com.autobots.automanager.entities.Address;
import com.autobots.automanager.entities.IdentityDocument;
import com.autobots.automanager.entities.PhoneNumber;
import com.autobots.automanager.repositories.CustomerRepository;

@SpringBootApplication
public class AutomanagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(AutomanagerApplication.class, args);
  }

  @Bean
  public ApplicationRunner dataInitializer(CustomerRepository repository) {
    return (ApplicationArguments args) -> {
      Calendar birthDate = Calendar.getInstance();
      birthDate.set(2002, Calendar.JUNE, 15); 
      
      Customer customer = new Customer();
      customer.setFullName("Pedro Alcântara de Bragança e Bourbon");
      customer.setSocialName("Dom Pedro");
      customer.setBirthDate(Calendar.getInstance().getTime());
      customer.setBirthDate(birthDate.getTime());

      PhoneNumber phone = new PhoneNumber();
      phone.setAreaCode("21");
      phone.setNumber("981234576");
      customer.getPhones().add(phone);

      Address address = new Address();
      address.setState("Rio de Janeiro");
      address.setCity("Rio de Janeiro");
      address.setAdditionalInfo("Copacabana");
      address.setStreet("Avenida Atlântica");
      address.setNumber("1702");
      address.setPostalCode("22021001");
      address.setAdditionalInfo("Hotel Copacabana Palace");
      customer.setAddress(address);

      IdentityDocument rg = new IdentityDocument();
      rg.setCategory("RG");
      rg.setValue("1500");

      IdentityDocument cpf = new IdentityDocument();
      cpf.setCategory("CPF");
      cpf.setValue("00000000001");

      customer.getDocuments().add(rg);
      customer.getDocuments().add(cpf);

      repository.save(customer);
    };
  }
}
