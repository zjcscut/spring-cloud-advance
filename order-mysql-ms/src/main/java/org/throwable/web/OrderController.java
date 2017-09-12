package org.throwable.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.throwable.common.Request;
import org.throwable.common.Response;
import org.throwable.common.entity.Order;
import org.throwable.common.vo.OrderCreateVo;
import org.throwable.common.vo.OrderUpdateVo;
import org.throwable.service.OrderService;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/12 17:38
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PutMapping(value = "/order", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Response<Order> createOrder(@RequestBody Request<OrderCreateVo> request) {
        Response<Order> response = new Response<>();
        if (null != request.getData()) {
            response.setData(orderService.createOrder(request.getData()));
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            return response;
        }
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return response;
    }

    @GetMapping(value = "/order", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Response<Order> fetchOrder(@RequestParam(value = "orderId") String orderId) {
        Response<Order> response = new Response<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        response.setData(orderService.fetchByOrderId(orderId));
        return response;
    }

    @PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Response<Order> updateOrder(@RequestBody Request<OrderUpdateVo> request) {
        Response<Order> response = new Response<>();
        if (null != request.getData()) {
            response.setData(orderService.updateOrder(request.getData()));
            response.setCode(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            return response;
        }
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return response;
    }

    @DeleteMapping(value = "/order", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Response<Order> deleteOrder(@RequestParam(value = "orderId") String orderId){
        Response<Order> response = new Response<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        response.setData(orderService.deleteOrder(orderId));
        return response;
    }
}
