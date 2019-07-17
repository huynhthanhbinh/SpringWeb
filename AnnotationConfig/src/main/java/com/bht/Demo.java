package com.bht;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.bht"})
public class Demo {
    public static void main(String[] args) {
        SpringApplication.run(Demo.class, args);
    }
}
