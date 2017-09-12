package org.throwable.common.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/12 11:29
 */
@Data
@ToString
@Document(collection = "order_collection")
public class Order implements Serializable{

    private static final long serialVersionUID = -1L;
    @Id
    private String orderCode;
    private Long userId;
    private Long amount;
    private Date createTime;
    private Integer status;
}
