package org.throwable.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.throwable.common.entity.User;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/12 10:18
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User fetchById(Long id) {
        final String sql = "SELECT * FROM t_user WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class), id);
    }

}
