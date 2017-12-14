package com.mvc.invite.model;

import com.mvc.invite.service.DateUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ywd-pc on 2017/12/11.
 */
@Setter
@Getter
@ToString
@Table(name = "ks_order")
public class KsOrder {

    public static final int STATUS_UNPAID = 0;
    public static final int STATUS_PAID = 1;
    public static final int STATUS_CONFIRMED = 2;
    public static final int STATUS_UNCONFIRMED = 3;
    public static final int STATUS_DELIVERING = 4;
    public static final int STATUS_FINISHED = 5;
    public static final int STATUS_EXPIRED = 6;

    private Integer id;
    private String name;
    private String cellphone;
    private String address;
    private Integer quantity;
    private Integer sum;
    private String inviteCode;
    private Integer payChannel;
    private String payAccount;
    private String payerName;
    private String comment;
    private Integer status;
    private Date createdAt;
    private Date updatedAt;

    private String createdAtStr;
    private String updatedAtStr;


    public KsOrder() { }

    public KsOrder(String name, String cellphone, String address, Integer quantity, Integer sum) {
        this.name = name;
        this.cellphone = cellphone;
        this.address = address;
        this.quantity = quantity;
        this.sum = sum;
    }

    public static void fillDateStr(KsOrder order) {
        order.setCreatedAtStr(DateUtil.DateToString(order.getCreatedAt(), "yyyy-MM-dd hh:mm:ss"));
        order.setUpdatedAtStr(DateUtil.DateToString(order.getUpdatedAt(), "yyyy-MM-dd hh:mm:ss"));
    }

    public static void fillDateStr(List<KsOrder> orders) {
        for (KsOrder order : orders) {
            fillDateStr(order);
        }
    }
}
