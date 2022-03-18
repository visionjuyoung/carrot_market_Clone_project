package com.hmsh.carrotmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CarrotMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarrotMarketApplication.class, args);
    }

}
