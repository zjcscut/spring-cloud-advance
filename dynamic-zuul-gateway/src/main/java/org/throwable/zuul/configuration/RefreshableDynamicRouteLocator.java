package org.throwable.zuul.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.util.StringUtils;
import org.throwable.zuul.dao.ZuulRouteEntityDao;
import org.throwable.zuul.entity.ZuulRouteEntity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/19 22:21
 */
@Slf4j
public class RefreshableDynamicRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

	private ZuulProperties zuulProperties;

	private ZuulRouteEntityDao zuulRouteEntityDao;

	public RefreshableDynamicRouteLocator(String servletPath, ZuulProperties properties) {
		super(servletPath, properties);
		this.zuulProperties = properties;
	}

	@Override
	protected Map<String, ZuulRoute> locateRoutes() {
		Map<String, ZuulRoute> routesMap = new LinkedHashMap<>();
		routesMap.putAll(super.locateRoutes());
		routesMap.putAll(locateRoutesFromDatabase());
		LinkedHashMap<String, ZuulRoute> values = new LinkedHashMap<>();
		for (Map.Entry<String, ZuulRoute> entry : routesMap.entrySet()) {
			String path = entry.getKey();
			// Prepend with slash if not already present.
			if (!path.startsWith("/")) {
				path = "/" + path;
			}
			if (StringUtils.hasText(this.zuulProperties.getPrefix())) {
				path = this.zuulProperties.getPrefix() + path;
				if (!path.startsWith("/")) {
					path = "/" + path;
				}
			}
			values.put(path, entry.getValue());
		}
		return values;
	}

	private Map<String, ZuulRoute> locateRoutesFromDatabase() {
		Map<String, ZuulRoute> routesMap = new LinkedHashMap<>();
		List<ZuulRouteEntity> routeEntities = this.zuulRouteEntityDao.queryAllAvailable();
		if (null != routeEntities && !routeEntities.isEmpty()){
			for (ZuulRouteEntity entity: routeEntities){
				if (StringUtils.hasText(entity.getPath()) || StringUtils.hasText(entity.getUrl())){
					ZuulRoute zuulRoute = new ZuulRoute();
					zuulRoute.setPath(entity.getPath());
					zuulRoute.setUrl(entity.getUrl());
					zuulRoute.setServiceId(entity.getServiceId());
					routesMap.put(zuulRoute.getPath(), zuulRoute);
				}
			}
		}
		return routesMap;
	}

	@Override
	public void refresh() {
       doRefresh();
	}

	public ZuulRouteEntityDao getZuulRouteEntityDao() {
		return zuulRouteEntityDao;
	}

	public void setZuulRouteEntityDao(ZuulRouteEntityDao zuulRouteEntityDao) {
		this.zuulRouteEntityDao = zuulRouteEntityDao;
	}
}
