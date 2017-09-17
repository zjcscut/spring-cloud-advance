package org.throwable.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.throwable.common.Response;
import org.throwable.common.dao.ZuulRouteEntityDao;
import org.throwable.common.entity.ZuulRouteEntity;

import java.util.List;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/14 12:24
 */
@Controller
public class ZuulRouteController {

    @Autowired
    private ZuulRouteEntityDao zuulRouteEntityDao;

    @GetMapping(value = "/routes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Response<List<ZuulRouteEntity>> queryAll() {
       return new Response<List<ZuulRouteEntity>>(){{
           this.setCode(200);
           this.setMessage("success!");
           this.setData(zuulRouteEntityDao.queryAllAvailable());
       }};
    }
}
