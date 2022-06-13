package com.github.ismail2ov.ecommerce.integration;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public class BaseTestcontainers {

  static final PostgreSQLContainer postgreSQLContainer;

  static {
    postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer("postgres:14")
        .withUsername("james")
        .withPassword("bond")
        .withDatabaseName("ecommerce")
        .withReuse(true);

    postgreSQLContainer.start();
  }

  @DynamicPropertySource
  static void postgresqlProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
  }
}
