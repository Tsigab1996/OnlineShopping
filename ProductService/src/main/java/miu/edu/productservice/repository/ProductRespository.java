package miu.edu.productservice.repository;


import miu.edu.productservice.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRespository extends MongoRepository<Product, Integer> {
}
