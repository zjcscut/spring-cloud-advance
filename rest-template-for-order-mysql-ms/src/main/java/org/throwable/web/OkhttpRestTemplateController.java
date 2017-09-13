package org.throwable.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.throwable.common.Request;
import org.throwable.common.Response;
import org.throwable.common.entity.Order;
import org.throwable.common.vo.OrderCreateVo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/13 11:48
 */
@Controller
public class OkhttpRestTemplateController {

    @Autowired
    @Qualifier(value = "okhttp3RestTemplate")
    private RestTemplate okhttp3RestTemplate;

    @GetMapping(value = "/okhttp/create-order", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Response<Order> createOrder() {
        Request<OrderCreateVo> request = new Request<>();
        String orderId = UUID.randomUUID().toString();
        request.setSerialNumber(orderId);
        OrderCreateVo orderCreateVo = new OrderCreateVo();
        orderCreateVo.setOrderId(orderId);
        orderCreateVo.setAmount(10000L);
        orderCreateVo.setUserId(10086L);
        request.setData(orderCreateVo);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Request<OrderCreateVo>> httpEntity = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<Response<Order>> exchange = okhttp3RestTemplate.exchange("http://localhost:9096/order", HttpMethod.PUT,
                httpEntity, new ParameterizedTypeReference<Response<Order>>() {
                });
        return exchange.getBody();
    }

    @GetMapping(value = "/okhttp/get-order", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Response<Order> getOrder() {
        String orderId = "e75c874e-6761-4471-a4eb-99c53482f4fb";
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("orderId", orderId);
        ResponseEntity<Response<Order>> exchange = okhttp3RestTemplate.exchange("http://localhost:9096/order?orderId={orderId}", HttpMethod.GET, null,
                new ParameterizedTypeReference<Response<Order>>() {
                }, uriVariables);
        return exchange.getBody();
    }
}
