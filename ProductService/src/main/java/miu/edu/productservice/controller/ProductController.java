package miu.edu.productservice.controller;

import java.util.List;

import miu.edu.productservice.domain.ProductDTO;
import miu.edu.productservice.feignClient.CustomerFeignClient;
import miu.edu.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

   @Autowired
   private CustomerFeignClient customerFeignClient;

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


//    @Value({"value1", "value2", "value3"})
//    @Value("${my.property.1}, ${my.property.2}, ${my.property.3}")

    @Value("${message}")
    private String message;



    @GetMapping("/message")
    public String showMessage() {

        return  message + customerFeignClient.getCustomerID();
    }

    @GetMapping("/get/{id}")
    public ProductDTO getProduct(@PathVariable int id) {
        return productService.getProduct(id);
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
