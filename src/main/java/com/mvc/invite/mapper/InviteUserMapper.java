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
    @Select("select * from invite_user where id=#{id}")
    InviteUser selectById(@Param("id") Integer id);

    @Select("select * from invite_user")
    List<InviteUser> selectAll();

    @Insert("insert into invite_user (name, cellphone, invite_code, sub_product) values (#{inviteUser.})")
    int insert(@Param("inviteUser") InviteUser inviteUser);
}
