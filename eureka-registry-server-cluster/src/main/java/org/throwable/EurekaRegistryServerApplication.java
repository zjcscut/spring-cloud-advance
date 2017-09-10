package org.throwable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/11 0:31
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaRegistryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaRegistryServerApplication.class, args);
	}
}
