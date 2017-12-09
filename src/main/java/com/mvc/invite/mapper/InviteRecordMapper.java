package com.mvc.invite.mapper;

import com.mvc.invite.model.InviteRecord;
import com.mvc.invite.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by ywd-pc on 2017/12/8.
 */
public interface InviteRecordMapper extends BaseMapper<InviteRecord> {

    @Select("select * from invite_record where unique_code = #{orderCode}")
    InviteRecord selectByCode(@Param("orderCode") String orderCode);

    @Insert("insert into invite_record (unique_code, invite_code, quantity) values" +
            " (#{order.orderCode}, #{order.memo}, #{order.quantity})")
    int insertInviteRecord(@Param("order") Order order);
}
