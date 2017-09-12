package org.throwable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/12 11:27
 */
@EnableEurekaClient
@SpringBootApplication
public class OrderMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderMsApplication.class, args);
    }
}