package com.bakesale.app;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.transaction.Transactional;

@Rollback
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class BaseIntegrationTest {
    private static final String POSTGRES_VERSION = "postgres:13";

    static final PostgreSQLContainer POSTGRESQL_CONTAINER;

    static {
        POSTGRESQL_CONTAINER =
                new PostgreSQLContainer(POSTGRES_VERSION).withDatabaseName("account_service");
        POSTGRESQL_CONTAINER.start();

    }
}
