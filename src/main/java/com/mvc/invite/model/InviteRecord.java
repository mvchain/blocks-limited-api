package com.mvc.invite.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by ywd-pc on 2017/12/7.
 */
public class InviteRecord {
    private Integer id;
    private String inviterCode;
    private Integer quantity;
    private String uniqueCode;
    private Date createdAt;
    private Date updateAt;


}
