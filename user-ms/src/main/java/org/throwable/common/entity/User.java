package org.throwable.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/12 10:15
 */
@Data
public class User implements Serializable{


    private static final long serialVersionUID = -1L;
    private Long id;
    private String name;


    private Date createTime;
    private Integer status;

}
