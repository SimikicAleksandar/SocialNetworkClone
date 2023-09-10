package com.example.swt_kwt_aleksandar_simikic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


import java.util.Arrays;


@SpringBootApplication
public class SwtKwtAleksandarSimikicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwtKwtAleksandarSimikicApplication.class, args);
    }

}
