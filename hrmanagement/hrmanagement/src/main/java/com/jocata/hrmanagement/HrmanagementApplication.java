package com.jocata.hrmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@EntityScan(basePackages = "com.jocata.hrmanagement.entity")
@SpringBootApplication
public class HrmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrmanagementApplication.class, args);
    }

}
