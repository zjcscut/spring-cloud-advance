package org.throwable.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.throwable.OrderMsApplication;
import org.throwable.common.entity.Order;

import java.util.Date;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/12 11:45
 */
@SpringBootTest(classes = OrderMsApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderDaoTest {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void saveOrder() throws Exception {
        Order order = new Order();
        order.setAmount(1L);
        order.setCreateTime(new Date());
        order.setOrderCode("orderCode");
        order.setUserId(1L);
        order.setStatus(1);
        order.setAmount(10000L);
        orderDao.saveOrder(order);
    }

    @Test
    public void fetchByOrderCode() throws Exception {
        Order order = orderDao.fetchByOrderCode("orderCode");
        System.out.println(order);
    }

    @Test
    public void updateOrder()throws Exception{
        Order order = orderDao.fetchByOrderCode("orderCode");
        System.out.println("before--->" + order);
        order.setAmount(20000L);
        orderDao.updateOrder(order);

        order = orderDao.fetchByOrderCode("orderCode");
        System.out.println("after--->" + order);
    }
}