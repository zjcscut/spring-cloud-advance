package org.throwable.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.throwable.common.Response;
import org.throwable.common.entity.Order;

import java.util.HashMap;
import java.util.Map;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/13 18:24
 */
@Controller
public class LoadBablanceSimpleRestTemplateController {

    private static final String APPLICATION_NAME = "order-mysql-ms";

    @Autowired
    @Qualifier(value = "loadBablanceSimpleRestTemplate")
    private RestTemplate loadBablanceSimpleRestTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @GetMapping(value = "/balance/get-order", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Response<Order> getOrder() {
        String orderId = "93c449f4-6118-44c0-94a2-9fbf50c654ba";
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("orderId", orderId);
        String url = String.format("http://%s/order?orderId={orderId}", APPLICATION_NAME);
        ResponseEntity<Response<Order>> exchange = loadBablanceSimpleRestTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<Response<Order>>() {
                }, uriVariables);
        return exchange.getBody();
    }

    @GetMapping(value = "/balance/info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Response<String> info() {
        Response<String> response = new Response<>();
        String info = "";
        ServiceInstance serviceInstance = loadBalancerClient.choose(APPLICATION_NAME);
        if (null != serviceInstance) {
            info = String.format("current serviceId:%s,host:%s,port:%s", serviceInstance.getServiceId(),
                    serviceInstance.getHost(), serviceInstance.getPort());
        }
        response.setData(info);
        return response;
    }
}
