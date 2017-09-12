package org.throwable.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/9/12 17:52
 */
@Data
@ToString
@NoArgsConstructor
public class OrderUpdateVo {

    private String orderId;
    private Long amount;
}
