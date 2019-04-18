package com.example.eshop.model.projection;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.Date;

public interface CustomerProjection {

    String getName();
    String getEmail();

    @Value("#{target.name + '_' + target.email}")
    String getCustomerNameWithEmail();

}
