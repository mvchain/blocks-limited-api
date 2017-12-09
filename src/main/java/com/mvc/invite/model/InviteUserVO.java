package com.mvc.invite.model;

import com.mvc.invite.service.DateUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ywd-pc on 2017/12/7.
 */
@Getter
@Setter
@ToString
public class InviteUserVO {

    private static final String format = "yyyy-MM-dd HH:mm:ss";

    private Integer id;
    private String name;
    private String cellphone;
    private String inviteCode;
    private String address;
    private Integer inviteCount;
    private String etherAddress;
    private String createdAt;
    private String updatedAt;

    public static InviteUserVO convert(InviteUser u) {
        InviteUserVO vo = new InviteUserVO();
        vo.id = u.getId();
        vo.name = u.getName();
        vo.cellphone = u.getCellphone();
        vo.inviteCode = u.getInviteCode();
        vo.address = u.getAddress();
        vo.inviteCount = u.getInviteCount();
        vo.etherAddress = u.getEtherAddress();
        vo.createdAt = DateUtil.DateToString(u.getCreatedAt(), format);
        vo.updatedAt = DateUtil.DateToString(u.getUpdatedAt(), format);
        return vo;
    }

    public static List<InviteUserVO> convert(List<InviteUser> mList) {
        List<InviteUserVO> voList = new ArrayList<>();
        for (InviteUser m : mList) {
            InviteUserVO vo = convert(m);
            voList.add(vo);
        }
        return voList;
    }
}
