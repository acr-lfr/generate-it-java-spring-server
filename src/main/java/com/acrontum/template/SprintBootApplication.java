package com.acrontum.template;

import com.acrontum.spring.lib.AcrontumConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * The type Sprint boot application.
 */
@SpringBootApplication
@Import(AcrontumConfig.class)
public class SprintBootApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SprintBootApplication.class, args);
    }

}
