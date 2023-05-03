package miu.edu.productservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

//    @Value({"value1", "value2", "value3"})
//    @Value("${my.property.1}, ${my.property.2}, ${my.property.3}")

    @Value("${message}")
    private String message;

    @GetMapping("/message")
    public String showMessage() {
        return message;
    }
}
