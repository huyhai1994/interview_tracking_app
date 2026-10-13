package com.homelab.interview_tracking_app.support;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.mysql.MySQLContainer;

import java.util.stream.Stream;


@Sql(
        scripts = "/sql/cleanup.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        config = @SqlConfig(
                transactionMode = SqlConfig.TransactionMode.ISOLATED
        )
)
public abstract class AbstractIntegrationTest {

    private static final String MYSQL_DATABASE_NAME =
            "interview_app";
    private static final MySQLContainer mysqlDb;


    static {
        mysqlDb = new MySQLContainer("mysql:8.0")
                .withDatabaseName(MYSQL_DATABASE_NAME)
                .withUsername("test")
                .withPassword("test")
                .withReuse(false);

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
    }
}