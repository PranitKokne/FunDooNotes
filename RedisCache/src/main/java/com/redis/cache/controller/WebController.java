package com.redis.cache.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redis.cache.model.Customer;
import com.redis.cache.repo.CustomerRepository;
import com.redis.cache.service.RedisService;

@RestController
public class WebController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private RedisService redisService;

	@RequestMapping("/save")
	public String save(@RequestBody Customer customer) {
		/*// save a single Customer
		customerRepository.save(new Customer("1", "Jack", "Smith"));
		customerRepository.save(new Customer("2", "Adam", "Johnson"));
		customerRepository.save(new Customer("3", "Kim", "Smith"));
		customerRepository.save(new Customer("4", "David", "Williams"));
		customerRepository.save(new Customer("5", "Peter", "Davis"));*/

		// redisService.getCustomer(customer);

		return "Done";
	}

	@RequestMapping("/findall")
	public String findAll() {
		String result = "";
		Map<String, Customer> customers = customerRepository.findAll();

		for (Customer customer : customers.values()) {
			result += customer.toString() + "<br>";
		}

		return result;
	}

	@RequestMapping("/find")
	public String findById(@RequestParam("id") String id) {
		String result = "";
		result = customerRepository.find(id).toString();
		return result;
	}

	@RequestMapping(value = "/uppercase")
	public String postCustomer(@RequestParam("id") String id) {
		Customer customer = customerRepository.find(id);
		customer.setFirstName(customer.getFirstName().toUpperCase());
		customer.setLastName(customer.getLastName().toUpperCase());

		customerRepository.update(customer);

		return "Done";
	}

	@RequestMapping("/delete")
	public String deleteById(@RequestParam("id") String id) {
		customerRepository.delete(id);

		return "Done";
	}
}
