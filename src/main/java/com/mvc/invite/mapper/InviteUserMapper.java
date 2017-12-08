package com.mvc.invite.mapper;

import com.mvc.invite.model.InviteUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by ywd-pc on 2017/12/8.
 */
public interface InviteUserMapper extends BaseMapper<InviteUser> {

    @Insert("insert into invite_user " +
            "(name, cellphone, invite_code) values " +
            "(#{inviteUser.name}, #{inviteUser.cellphone}, #{inviteUser.inviteCode})")
    int insert(@Param("inviteUser") InviteUser inviteUser);

    @Select("select * from invite_user where id=#{id}")
    InviteUser selectById(@Param("id") Integer id);

    @Select("select * from invite_user order by invite_count desc")
    List<InviteUser> selectAll();

    @Select("select * from invite_user where invite_code=#{code}")
    InviteUser selectByCode(@Param("code") String code);

    @Select("select * from invite_user where cellphone=#{cellphone}")
    InviteUser selectByPhone(@Param("cellphone") String cellphone);
}
