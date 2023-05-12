package miu.edu.orderservice;

import miu.edu.orderservice.domain.Product;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderController {
     private Product product;

    @GetMapping("/order")
    public Product getOrder( ) {
        return product;
    }
    @KafkaListener(topics = "product")
    public Product getProduct(String topic, final Product products ) {
        product=products;
        return product;
    }



}
