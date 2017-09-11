package org.throwable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/11 0:31
 */
@RestController
@EnableEurekaServer
@SpringBootApplication
public class EurekaRegistryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaRegistryServerApplication.class, args);
	}

	@Bean
	public HealthIndicator oneHealthIndicator() {
		return () -> Health.up().withDetail("one", "success").build();
	}

	@Autowired
	private CounterService counterService;

	@GetMapping(value = "/hello")
	public String hello() {
		counterService.increment("throwable.hello.count");
		return "success";
	}


}
