package com.ciandt.poc.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

/**
 * Created by guilherme.roveri on 4/24/16.
 */
@SpringBootApplication
@EnableCircuitBreaker
public class PocMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PocMongoApplication.class, args);
    }
}
