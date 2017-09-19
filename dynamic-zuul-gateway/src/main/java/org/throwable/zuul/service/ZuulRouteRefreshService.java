package org.throwable.zuul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/19 22:34
 */
@Service
public class ZuulRouteRefreshService {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private RouteLocator routeLocator;

	public void refreshRoutes(){
		applicationEventPublisher.publishEvent(new RoutesRefreshedEvent(routeLocator));
	}
}
