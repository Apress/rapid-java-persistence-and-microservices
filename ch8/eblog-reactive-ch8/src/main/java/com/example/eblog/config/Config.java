package com.example.eblog.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

@Configuration
public class Config extends AbstractR2dbcConfiguration {

    @Override
    public PostgresqlConnectionFactory connectionFactory() {
        PostgresqlConnectionConfiguration connectionConfiguration =
                PostgresqlConnectionConfiguration.builder()
                        .applicationName("eblog")
                        .database("eblog")
                        .host("localhost")
                        .username("postgres")
                        .password("postgres").build();

        return new PostgresqlConnectionFactory(connectionConfiguration);
    }

}
