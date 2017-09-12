package org.throwable.common;

import lombok.Data;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/12 17:50
 */
@Data
public class Response<T> {

    private Integer code;
    private String message;
    private T data;
}
