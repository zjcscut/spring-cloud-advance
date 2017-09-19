package org.throwable.zuul.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.throwable.zuul.utils.JacksonLocalDateTimeFormatter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/14 10:21
 */
@Data
public class ZuulRouteEntity implements Serializable {

    private static final long serialVersionUID = -1;

    private Long id;
    private String path;
    private String serviceId;
    private String url;
    private Integer status;

    @JsonSerialize(using = JacksonLocalDateTimeFormatter.class)
    private LocalDateTime createTime;

}
