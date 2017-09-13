package org.throwable.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.AsyncRestTemplate;
import org.throwable.common.Response;
import org.throwable.common.entity.Order;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/13 23:13
 */
@Controller
@Slf4j
public class AsyncSimpleRestTemplateController {

	@Autowired
	private AsyncRestTemplate asyncRestTemplate;

	@GetMapping(value = "/async/get-order", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Response getOrder() {
		String orderId = "orderCode";
		ListenableFuture<ResponseEntity<Response<Order>>> future = asyncRestTemplate.exchange("http://localhost:9096/order?orderId={orderId}", HttpMethod.GET, null,
				new ParameterizedTypeReference<Response<Order>>() {
				}, orderId);
		future.addCallback(new ListenableFutureCallback<ResponseEntity<Response<Order>>>() {

			@Override
			public void onFailure(Throwable ex) {
				log.error("Send get http to http://localhost:9096/order failed", ex);
			}

			@Override
			public void onSuccess(ResponseEntity<Response<Order>> result) {
				//这里为了方便看结果打印了error日志
				log.error("Receive response --> {}", result);
			}
		});
		return new Response() {{
			this.setCode(200);
			this.setMessage("async success!");
		}};
	}
}
