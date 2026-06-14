package org.starry.webmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

/**
 * Main Spring Boot entry point for the web management backend.
 */
@ServletComponentScan
@SpringBootApplication
public class WebManagementApplication {

    /**
     * Starts the Spring Boot application.
     */
    public static void main(String[] args) {
        SpringApplication.run(WebManagementApplication.class, args);
    }

}
