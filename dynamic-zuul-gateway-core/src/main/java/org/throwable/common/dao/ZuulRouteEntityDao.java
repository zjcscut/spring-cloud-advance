package org.throwable.common.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.throwable.common.entity.ZuulRouteEntity;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/14 12:05
 */
@Repository
public class ZuulRouteEntityDao {

    private final BeanPropertyRowMapper<ZuulRouteEntity> rowMapper = BeanPropertyRowMapper.newInstance(ZuulRouteEntity.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ZuulRouteEntity save(ZuulRouteEntity zuulRouteEntity) {
        final String sql = "INSERT INTO t_zuul_route(path,serviceId,url,createTime,status) VALUES(?,?,?,?,?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, zuulRouteEntity.getPath());
            preparedStatement.setObject(2, zuulRouteEntity.getServiceId());
            preparedStatement.setObject(3, zuulRouteEntity.getUrl());
            preparedStatement.setObject(4, zuulRouteEntity.getCreateTime());
            preparedStatement.setObject(5, zuulRouteEntity.getStatus());
            return preparedStatement;
        }, keyHolder);
        zuulRouteEntity.setId(keyHolder.getKey().longValue());
        return zuulRouteEntity;
    }

    public List<ZuulRouteEntity> queryAllAvailable() {
        final String sql = "SELECT * FROM t_zuul_route WHERE status = 1";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public int update(ZuulRouteEntity zuulRouteEntity) {
        final String sql = "UPDATE t_zuul_route SET path = ?,serviceId = ?,url = ?,status = ?";
        return jdbcTemplate.update(sql, zuulRouteEntity.getPath(), zuulRouteEntity.getServiceId(),
                zuulRouteEntity.getUrl(), zuulRouteEntity.getStatus());
    }
}
