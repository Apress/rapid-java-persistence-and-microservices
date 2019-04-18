package com.example.eshop.web;

import com.example.eshop.model.Order;
import com.example.eshop.service.impl.CustomerService;
import com.example.eshop.service.impl.OrderService;
import com.example.eshop.service.impl.ProductService;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "Order a product", response = Boolean.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Order created successfully"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping(value = "/api/orders", produces = "application/json")
    //@PreAuthorize("#oauth2.hasScope('read')")
    public ResponseEntity<?> purchaseSampleProduct()  throws Exception {

        customerService.registerNewCustomers();
        productService.registerNewProducts();

        Order order = orderService.orderProduct();

        if(Objects.isNull(order)) {
            log.error("Transactions are failing, please check after some time");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("Order status: {}", Objects.isNull(order));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(order.getOrderId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/applications")
    public Applications getApplications() {
        return eurekaClient.getApplications();
    }

    @GetMapping("/applicationInstances")
    public List<ServiceInstance> getApplicationInstances(HttpServletRequest request) {
        log.info("Services: {}", discoveryClient.getServices());
        log.info("JwtToken: {}", request.getHeader("Authorization"));
        return discoveryClient.getInstances("inventory-service");
    }

}
