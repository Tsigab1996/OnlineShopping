package miu.edu.productservice.controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import miu.edu.productservice.domain.Product;
import miu.edu.productservice.domain.ProductDTO;
import miu.edu.productservice.feignClient.CustomerFeignClient;
import miu.edu.productservice.integration.Sender;
import miu.edu.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {


    @Autowired
    private CustomerFeignClient customerFeignClient;

    private final ProductService productService;

//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private Sender sender;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


//    @Value({"value1", "value2", "value3"})
//    @Value("${my.property.1}, ${my.property.2}, ${my.property.3}")

    @Value("${message}")
    private String message;


    @GetMapping("/message")
    @CircuitBreaker(name = "ProductController", fallbackMethod = "showMessageFallBackMethod")
    public String showMessage() {
        return message + customerFeignClient.getCustomerID();
    }

    public String showMessageFallBackMethod(Exception exception){
        System.out.println("Fall back method called!");
        return exception.getMessage();
    }

    @GetMapping("/get/{id}")
    public ProductDTO getProduct(@PathVariable int id) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
        ProductDTO product = productService.getProduct(id);
        sender.send("topicA", product.toString());
//        String productString = objectMapper.writeValueAsString(product);
//        kafkaTemplate.send("topicA", productString);
        System.out.println("Product sent");
        return product;
    }

    @GetMapping("/get")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/add")
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ProductDTO deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

    @PostMapping("/update")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO);

    }

//    @FeignClient(name ="Customer")
//    // @LoadBalancerClient
//    public interface CustomerFeignClient {
//        @GetMapping("/api/v1/customers/load")
//        int getCustomerID();
//    }

}
