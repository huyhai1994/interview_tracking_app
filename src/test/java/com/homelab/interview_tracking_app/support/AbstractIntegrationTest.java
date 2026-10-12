package com.homelab.interview_tracking_app.support;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.mysql.MySQLContainer;

import java.util.stream.Stream;

@Testcontainers
public abstract class AbstractIntegrationTest {

    private static final String MYSQL_DATABASE_NAME =
            "interview_app";
    private static final MySQLContainer mysqlDb;


    static {
        mysqlDb = new MySQLContainer("mysql:8.0")
                .withDatabaseName(MYSQL_DATABASE_NAME)
                .withUsername("test")
                .withPassword("test");

        Startables.deepStart(
                Stream.of(
                        mysqlDb
                )
        ).join();
    }

    @DynamicPropertySource
    static void registerProperties(
            DynamicPropertyRegistry registry
    ) {
        registry.add(
                "spring.datasource.username",
                mysqlDb::getUsername
        );

        registry.add(
                "spring.datasource.password",
                mysqlDb::getPassword
        );

        registry.add(
                "spring.datasource.url",
                mysqlDb::getJdbcUrl
        );

        registry.add(
                "spring.data.redis.database",
                () -> 0
        );

        registry.add(
                "spring.data.redis.connect-timeout",
                () -> "2s"
        );

        registry.add(
                "spring.data.redis.timeout",
                () -> "2s"
        );
    }
}