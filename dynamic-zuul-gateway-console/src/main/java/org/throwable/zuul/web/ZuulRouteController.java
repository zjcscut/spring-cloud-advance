package org.throwable.zuul.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.throwable.common.Response;
import org.throwable.zuul.common.constants.RouteStatus;
import org.throwable.zuul.common.model.ZuulRoutePostForm;
import org.throwable.zuul.common.model.ZuulRoutePutForm;
import org.throwable.zuul.dao.ZuulRouteEntityDao;
import org.throwable.zuul.entity.ZuulRouteEntity;

import java.time.LocalDateTime;
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
		return new Response<List<ZuulRouteEntity>>() {{
			this.setCode(200);
			this.setMessage("success!");
			this.setData(zuulRouteEntityDao.queryAllAvailable());
		}};
	}

	@PostMapping(value = "/route", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Response<ZuulRouteEntity> add(ZuulRoutePostForm zuulRoutePostForm) {
		Response<ZuulRouteEntity> response = new Response<>();
		response.setCode(200);
		response.setMessage("success");
		ZuulRouteEntity entity = new ZuulRouteEntity();
		entity.setCreateTime(LocalDateTime.now());
		entity.setStatus(RouteStatus.VALID.getValue());
		entity.setPath(zuulRoutePostForm.getPath());
		entity.setServiceId(zuulRoutePostForm.getServiceId());
		entity.setUrl(zuulRoutePostForm.getUrl());
		entity = zuulRouteEntityDao.save(entity);
		response.setData(entity);
		return response;
	}

	@GetMapping(value = "/route", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Response<ZuulRouteEntity> get(@RequestParam(value = "id") Long id) {
		Response<ZuulRouteEntity> response = new Response<>();
		response.setCode(200);
		response.setMessage("success");
		response.setData(zuulRouteEntityDao.fetchById(id));
		return response;
	}

	@PutMapping(value = "/route", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Response<ZuulRouteEntity> put(ZuulRoutePutForm zuulRoutePutForm) {
		Response<ZuulRouteEntity> response = new Response<>();
		ZuulRouteEntity entity = zuulRouteEntityDao.fetchById(zuulRoutePutForm.getId());
		if (null != entity) {
			entity.setServiceId(zuulRoutePutForm.getServiceId());
			entity.setUrl(zuulRoutePutForm.getUrl());
			entity.setStatus(zuulRoutePutForm.getStatus());
			entity.setPath(zuulRoutePutForm.getPath());
			zuulRouteEntityDao.update(entity);
			response.setCode(200);
			response.setMessage("success");
		}else {
			response.setCode(400);
			response.setMessage("not exist");
		}
		return response;
	}
}
