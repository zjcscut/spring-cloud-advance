package org.throwable.zuul.common.model;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/18 22:34
 */
public class ZuulRoutePostForm {

	private String path;
	private String serviceId;
	private String url;

	public ZuulRoutePostForm() {
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
