package miu.edu.orderservice;

import miu.edu.orderservice.domain.ProductDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController {
    private ProductDTO product;

    @GetMapping()
    public ProductDTO getOrder() {
        return product;
    }

    @KafkaListener(topics = "topicA")
    public ProductDTO getProduct(String topic, final ProductDTO products) {
        product = products;
        System.out.println("products received");
        return product;
    }

}
