package com.example.swt_kwt_aleksandar_simikic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import java.util.Arrays;


@SpringBootApplication
@EnableWebMvc
public class SwtKwtAleksandarSimikicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwtKwtAleksandarSimikicApplication.class, args);
    }

}
