package miu.edu.productservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
@FeignClient(name ="Customer")
public interface CustomerFeignClient {
       @GetMapping("/api/v1/customers/load")
        int getCustomerID();
}
