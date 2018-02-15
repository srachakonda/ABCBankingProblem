package com.turvo.abcbanking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ABCBankingApplication {
    private final static Logger LOG = LoggerFactory.getLogger(ABCBankingApplication.class);

    public static void main(String[] args) {
        LOG.info("Starting ABC Banking Application");
        SpringApplication.run(ABCBankingApplication.class, args);
    }
}
