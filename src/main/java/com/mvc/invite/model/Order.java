package com.mvc.invite.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by ywd-pc on 2017/12/8.
 */
@Getter
@Setter
@ToString
public class Order {
    private String orderCode;
    private String status;
    private String memo;
    private Integer quantity;

    public Order(String orderCode, String status, Integer quantity, String memo) {
        this.orderCode = orderCode;
        this.status = status;
        this.memo = memo;
        this.quantity = quantity;
    }
}
