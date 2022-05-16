package com.acrontum.template;

import java.io.File;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * The type Postgres docker env.
 */
@Testcontainers
@ActiveProfiles("postgres")
public abstract class PostgresDockerEnv {

    public static final DockerComposeContainer DOCKER_COMPOSE_CONTAINER;

    static {
        DOCKER_COMPOSE_CONTAINER =
                new DockerComposeContainer(new File("src/test/resources/docker-compose.yml"))
                        .withExposedService("db", 5432, Wait.forListeningPort());

        DOCKER_COMPOSE_CONTAINER.start();
    }
}
