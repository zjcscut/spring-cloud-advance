package org.throwable.zuul.configuration;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.throwable.zuul.dao.ZuulRouteEntityDao;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/19 22:36
 */
@Configuration
public class DynamicZuulRouteConfiguration {

	@Bean
	public RefreshableDynamicRouteLocator refreshableDynamicRouteLocator(ServerProperties serverProperties,
																		 ZuulProperties zuulProperties,
																		 ZuulRouteEntityDao zuulRouteEntityDao) {
		RefreshableDynamicRouteLocator routeLocator
				= new RefreshableDynamicRouteLocator(serverProperties.getServletPrefix(), zuulProperties);
		routeLocator.setZuulRouteEntityDao(zuulRouteEntityDao);
		return routeLocator;
	}

}
