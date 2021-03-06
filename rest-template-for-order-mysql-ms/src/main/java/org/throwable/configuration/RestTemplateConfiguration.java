package org.throwable.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/12 22:26
 */
@Configuration
public class RestTemplateConfiguration {

	//基于jdk原生的UrlConnection,注意同一个类的多个Bean实例必须指定"主"Bean
	@Primary
	@Bean
	public RestTemplate simpleRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(10000);
		requestFactory.setConnectTimeout(10000);
		restTemplate.setRequestFactory(requestFactory);
		return restTemplate;
	}

	//基于apache的httpclient
	@Bean
	public RestTemplate apacheRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectionRequestTimeout(10000);
		requestFactory.setReadTimeout(10000);
		requestFactory.setConnectTimeout(10000);
		restTemplate.setRequestFactory(requestFactory);
		return restTemplate;
	}

	//基于okhttp3
	@Bean
	public RestTemplate okhttp3RestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		OkHttp3ClientHttpRequestFactory requestFactory = new OkHttp3ClientHttpRequestFactory();
		requestFactory.setConnectTimeout(10000);
		requestFactory.setReadTimeout(10000);
		requestFactory.setWriteTimeout(10000);
		restTemplate.setRequestFactory(requestFactory);
		return restTemplate;
	}

	@Bean
	@LoadBalanced
	public RestTemplate loadBalanceSimpleRestTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(10000);
		requestFactory.setConnectTimeout(10000);
		restTemplate.setRequestFactory(requestFactory);
		return restTemplate;
	}

	@Bean
	public AsyncRestTemplate asyncRestTemplate(){
		AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(10000);
		requestFactory.setConnectTimeout(10000);
		requestFactory.setTaskExecutor(new SimpleAsyncTaskExecutor()); //必须设置异步任务执行器
		asyncRestTemplate.setAsyncRequestFactory(requestFactory);
		return asyncRestTemplate;
	}
}
