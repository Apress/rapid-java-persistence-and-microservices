package com.example.eshop;

import com.example.eshop.model.dto.CustomerOrder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EshopAppTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testThroughJdbcTemplate()   {
        String sql = "Select " +
                "c.customerId, o.orderId, p.productId," +
                "c.name as customerName, c.email as customerEmail, p.name as productName," +
                "p.quantity as quantity, p.price as price " +
                "from " +
                "Customer c inner join `Order` o on c.customerId = o.customerId inner join " +
                "Product p on o.productId = p.productId";

        List<CustomerOrder> customerWithOrdersList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CustomerOrder.class));
        log.info("Order Details: " + customerWithOrdersList);

    }
}
