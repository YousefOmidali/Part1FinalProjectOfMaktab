package service;

import entity.Customer;
import repository.CustomerRepository;

import java.util.List;

public class CustomerService {
    CustomerRepository customerRepository = new CustomerRepository();

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public void update(Customer customer) {
        customerRepository.update(customer);
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer login(String username, String password) {
        return customerRepository.login(username, password);
    }

    public List<Customer> gridSearch(String firstName,
                                     String lastName,
                                     String email,
                                     String username) {
        return customerRepository.gridSearch(firstName, lastName, email, username);
    }
}
