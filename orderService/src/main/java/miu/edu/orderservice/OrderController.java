package miu.edu.orderservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import miu.edu.orderservice.domain.ProductDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController {
    private ProductDTO product;
    String message;

    @GetMapping()
    public String getOrder() {
        return message + "Hi";
    }

    @KafkaListener(topics = "topicA")
    public String getProduct(@Payload final String products) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("message received ");
//        product = objectMapper.readValue(products, ProductDTO.class);
        System.out.println("products received");
        message = products;
        return products;
    }

}
