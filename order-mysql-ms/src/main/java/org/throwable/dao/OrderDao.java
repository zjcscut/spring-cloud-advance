package org.throwable.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.throwable.common.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/12 11:34
 */
@Repository
public class OrderDao {

	private final BeanPropertyRowMapper<Order> beanPropertyRowMapper = BeanPropertyRowMapper.newInstance(Order.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Order saveOrder(Order order) {
		final String sql = "INSERT INTO t_order(orderCode,userId,amount,createTime,status) VALUES (?,?,?,?,?)";
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, order.getOrderCode());
			ps.setObject(2, order.getUserId());
			ps.setObject(3, order.getAmount());
			ps.setObject(4, order.getCreateTime());
			ps.setObject(5, order.getStatus());
			return ps;
		}, keyHolder);
		order.setId(keyHolder.getKey().longValue());
		return order;
	}

	public Order fetchByOrderCode(String orderCode) {
		final String sql = "SELECT * FROM t_order WHERE orderCode = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{orderCode}, beanPropertyRowMapper);
	}

	public int updateOrder(Order order) {
		final String sql = "UPDATE t_order SET amount = ?,status = ? WHERE orderCode = ?";
		return jdbcTemplate.update(sql, order.getAmount(), order.getStatus(), order.getOrderCode());
	}
}
