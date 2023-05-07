package miu.edu.customer.service;

import miu.edu.customer.domain.Customer;
import miu.edu.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);

    }
    public Customer getCustomer(int id) {
        return customerRepository.findById(id).get();
    }
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }
    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

}
