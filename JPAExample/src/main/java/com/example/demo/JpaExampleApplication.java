package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaExampleApplication {

	private static final Logger log = LoggerFactory.getLogger(JpaExampleApplication.class);

	  public static void main(String[] args) {
	    SpringApplication.run(JpaExampleApplication.class);
	  }

	  @Bean
	  public CommandLineRunner demo(CustomerRepository repository) {
	    return (args) -> {
	      // save a few customers
	      repository.save(new Customer("Jack", "Bauer"));
	      repository.save(new Customer("Chloe", "O'Brian"));
	      repository.save(new Customer("Kim", "Bauer"));
	      repository.save(new Customer("David", "Palmer"));
	      repository.save(new Customer("Michelle", "Dessler"));

	      // fetch all customers
	      log.info("Customers found with findAll():");
	      log.info("-------------------------------");
	      for (Customer customer : repository.findAll()) {
	        log.info(customer.toString());
	      }
	      log.info("");

	      // fetch an individual customer by ID
	      Customer customer = repository.findById(1L);
	      log.info("Customer found with findById(1L) ready to delete:");
	      log.info("--------------------------------");
	      log.info(customer.toString());
	      repository.deleteById(customer.getId());
	      log.info("");
	      
	      // fetch all customers
	      log.info("Customers found with findAll():");
	      log.info("-------------------------------");
	      for (Customer customer2 : repository.findAll()) {
	        log.info(customer2.toString());
	      }
	      log.info("");

	      // fetch customers by last name
	      log.info("Customer found with findByLastName('Bauer'):");
	      log.info("--------------------------------------------");
	      repository.findByLastName("Bauer").forEach(bauer -> {
	        log.info(bauer.toString());
	      });
	      // for (Customer bauer : repository.findByLastName("Bauer")) {
	      //  log.info(bauer.toString());
	      // }
	      
	   // fetch customers by first name
	      log.info("");
	      log.info("Customer found with findByFirstName('David'):");
	      log.info("--------------------------------------------");
	      repository.findByFirstName("David").forEach(david -> {
	        log.info(david.toString());
	        david.setFirstName("Manuel");
	        repository.save(david);
	      });
	      log.info("");
	      
	      // fetch all customers
	      log.info("Customers found with findAll():");
	      log.info("-------------------------------");
	      for (Customer customer3 : repository.findAll()) {
	        log.info(customer3.toString());
	      }
	      log.info("");
	    };    
	  }
}
