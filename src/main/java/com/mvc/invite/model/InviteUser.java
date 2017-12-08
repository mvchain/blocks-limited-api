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
    private String address;
    private Integer inviteCount;
    private Date createdAt;
    private Date updatedAt;

    public InviteUser() {}

    public InviteUser(String name, String cellphone, String address) {
        this.name = name;
        this.cellphone = cellphone;
        this.address = address;
        this.inviteCount = 0;
    }


}
