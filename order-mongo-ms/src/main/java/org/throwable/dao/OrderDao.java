package org.throwable.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.throwable.common.entity.Order;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/12 11:34
 */
@Repository
public class OrderDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveOrder(Order order) {
        mongoTemplate.save(order);
    }

    public Order fetchByOrderCode(String orderCode) {
        Query query = new Query();
        query.addCriteria(Criteria.where("orderCode").is(orderCode));
        return mongoTemplate.findOne(query, Order.class);
    }

    public int updateOrder(Order order) {
        Query query = new Query();
        query.addCriteria(Criteria.where("orderCode").is(order.getOrderCode()));
        Update update = new Update();
        update.set("userId", order.getUserId())
                .set("amount", order.getAmount())
                .set("createTime", order.getCreateTime())
                .set("status", order.getStatus());
        return mongoTemplate.updateFirst(query, update, Order.class).getN();
    }
}
