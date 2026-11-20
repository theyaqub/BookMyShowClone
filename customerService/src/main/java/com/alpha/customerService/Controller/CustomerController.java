package com.alpha.customerService.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alpha.customerService.Service.CustomerService;
import com.alpha.customerService.dto.CustomerDTO;
import com.alpha.customerService.dto.MovieResponseDTO;
import com.alpha.customerService.entity.Customer;
import com.alpha.customerService.response.ResponseStructure;



@RestController

public class CustomerController {
	
	@Autowired
	private CustomerService CustomerService;
	@PostMapping("/Customer/CreateAccount")	
	public ResponseEntity<ResponseStructure<Customer>> CreateCustomer(@RequestBody Customer customer) {
		return CustomerService.createCustomerAccount(customer);
		
	}
	
	
	@GetMapping("/Customer/Find")
	public ResponseEntity<ResponseStructure<Customer>> findCustomer(@RequestParam long id) {
		return CustomerService.findCustomerById(id);

	}
	
	@DeleteMapping("/Customer/delete")
	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(@RequestParam long id) {
		 return CustomerService.deleteCustomerById(id);
	}
	
	@GetMapping("/customer/findMovieByName")
	public Object findMovieByname(@RequestParam String MovieName) {
		return CustomerService.findMovieByNameService(MovieName);
	}
}