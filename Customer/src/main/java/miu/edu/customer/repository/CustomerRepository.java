package miu.edu.customer.repository;

import miu.edu.customer.domain.Customer;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Integer> {

}
