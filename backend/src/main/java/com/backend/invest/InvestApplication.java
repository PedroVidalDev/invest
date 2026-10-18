package com.backend.invest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class InvestApplication {

  public static void main(String[] args) {
    SpringApplication.run(InvestApplication.class, args);
  }

}
