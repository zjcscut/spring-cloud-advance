package org.throwable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/14 10:17
 */
@EnableEurekaClient
@SpringBootApplication
public class DynamicZuulGatewayConsoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicZuulGatewayConsoleApplication.class, args);
    }
}
