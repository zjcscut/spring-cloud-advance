package org.throwable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/14 10:17
 */
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class DynamicZuulGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicZuulGatewayApplication.class, args);
    }
}
