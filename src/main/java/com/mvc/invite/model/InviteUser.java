package com.mvc.invite.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by ywd-pc on 2017/12/7.
 */
@Getter
@Setter
@ToString
public class InviteUser {
    private Integer id;
    private String name;
    private String cellphone;
    private String inviteCode;
    private Integer subProductCount;
    private Date createdAt;
    private Date updatedAt;
}
