package org.throwable;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/11 22:51
 */
@EnableEurekaClient
@EnableAdminServer
@SpringBootApplication
public class AdminConsoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminConsoleApplication.class, args);
	}
}