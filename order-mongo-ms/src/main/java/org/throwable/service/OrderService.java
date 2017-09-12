package org.throwable.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.throwable.common.entity.Order;
import org.throwable.common.vo.OrderCreateVo;
import org.throwable.common.vo.OrderUpdateVo;
import org.throwable.dao.OrderDao;

import java.util.Date;
import java.util.UUID;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/12 18:03
 */
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    public Order createOrder(OrderCreateVo orderCreateVo){
        Order newOrder = new Order();
        newOrder.setAmount(orderCreateVo.getAmount());
        newOrder.setUserId(orderCreateVo.getUserId());
        newOrder.setCreateTime(new Date());
        newOrder.setOrderCode(UUID.randomUUID().toString());
        newOrder.setStatus(1);
        orderDao.saveOrder(newOrder);
        return newOrder;
    }

    public Order fetchByOrderId(String orderId){
        return orderDao.fetchByOrderCode(orderId);
    }

    public Order updateOrder(OrderUpdateVo orderUpdateVo){
        Order order = orderDao.fetchByOrderCode(orderUpdateVo.getOrderId());
        if (null != order){
            order.setAmount(orderUpdateVo.getAmount());
            orderDao.updateOrder(order);
        }
        return order;
    }

    public Order deleteOrder(String orderId){
        Order order = orderDao.fetchByOrderCode(orderId);
        if (null != order){
            order.setStatus(0);
            orderDao.updateOrder(order);
        }
        return order;
    }
}
