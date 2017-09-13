package org.throwable.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/13 23:46
 */
@Component
@Slf4j
public class CustomZuulFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		if (log.isInfoEnabled()){
			log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
		}
		String accessToken = request.getParameter("accessToken");
		if (null == accessToken){
			log.warn("accessToken is null!");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.setResponseBody("accessToken is invalid!");
			return null;
		}
		log.warn("ok!");
		return null;
	}
}
