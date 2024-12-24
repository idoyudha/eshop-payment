package com.github.eshop.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {
        "com.github.eshop.payment.infrastructure",
        "com.github.eshop.payment.application",
        "com.github.eshop.payment.domain",
        "com.github.eshop.payment.controller"
})
@EnableKafka
@EnableTransactionManagement
@EntityScan(basePackages = "com.github.eshop.payment.infrastructure.persistence.entity")
@EnableJpaRepositories(basePackages = "com.github.eshop.payment.infrastructure.persistence.repository")
public class PaymentServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}
