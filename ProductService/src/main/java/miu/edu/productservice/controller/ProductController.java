package miu.edu.productservice.controller;

import java.util.List;

import miu.edu.productservice.domain.ProductDTO;
import miu.edu.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

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
        return message;
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

}
