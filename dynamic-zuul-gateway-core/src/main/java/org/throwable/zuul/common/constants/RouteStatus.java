package org.throwable.zuul.common.constants;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/18 22:53
 */
public enum RouteStatus {

	VALID(1),

	INVALID(0);

	private int value;

	RouteStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
