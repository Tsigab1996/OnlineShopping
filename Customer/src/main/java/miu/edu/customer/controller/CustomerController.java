package miu.edu.customer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @Value("${message}")
    private String message;

    @GetMapping("/message")
    public String showMesssage() {
        return message;
    }
}
