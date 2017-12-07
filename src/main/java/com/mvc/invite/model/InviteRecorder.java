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
public class InviteRecorder {
    private Integer id;
    private Integer inviterId;
    private Integer inviteeId;
    private Integer productCount;
    private Date transactionAt;
    private Date createdAt;
    private Date updateAt;
}
