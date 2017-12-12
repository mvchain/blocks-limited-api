package com.mvc.invite.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by ywd-pc on 2017/12/11.
 */
@Setter
@Getter
@ToString
public class KsOrder {

    private String name;
    private String cellphone;
    private String address;
    private Integer quantity;
    private Date createdAt;
    private Date updatedAt;

    public KsOrder(String name, String cellphone, String address, Integer quantity) {
        this.name = name;
        this.cellphone = cellphone;
        this.address = address;
        this.quantity = quantity;
    }
}
