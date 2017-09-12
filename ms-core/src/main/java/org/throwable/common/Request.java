package org.throwable.common;

import lombok.Data;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/12 17:49
 */
@Data
public class Request<T> {

    private String serialNumber;
    private T data;
}
