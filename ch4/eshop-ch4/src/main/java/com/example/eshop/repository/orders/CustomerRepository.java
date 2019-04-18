package com.example.eshop.repository.orders;

import com.example.eshop.model.dto.CustomerDto;
import com.example.eshop.model.orders.Customer;
import com.example.eshop.model.orders.Order;
import com.example.eshop.model.projection.CustomerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

    /* N+1 example */
    @Query("Select c from Customer c left join fetch c.orders")
    List<Customer> findCustomersWithOrderDetails();

    // Query example with Constructor mapping
    @Query(value =
            "Select new com.example.eshop.model.dto.CustomerDto(c.id, c.name) from Customer c")
    List<CustomerDto> findCustomers();

    // Projections examples
    @Query("Select c from Customer c")
    List<CustomerProjection> findAllCustomers();
    CustomerProjection findOneByName(String name);

}
