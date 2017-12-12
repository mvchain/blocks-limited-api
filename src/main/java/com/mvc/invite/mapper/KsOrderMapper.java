package com.mvc.invite.mapper;

import com.mvc.invite.model.KsOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by ywd-pc on 2017/12/11.
 */
public interface KsOrderMapper extends BaseMapper<KsOrder> {

    @Insert("insert into ks_order (name, cellphone, address, quantity, sum, invite_code, comment, status) values " +
            "(#{ksOrder.name},#{ksOrder.cellphone},#{ksOrder.address},#{ksOrder.quantity}," +
            " #{ksOrder.sum},#{ksOrder.inviteCode},#{ksOrder.comment},#{ksOrder.status})")
    @Options(useGeneratedKeys = true, keyProperty = "ksOrder.id")
    int insertKsOrder(@Param("ksOrder") KsOrder ksOrder);

    @Select("select * from ks_order where id=#{id}")
    KsOrder selectById(@Param("id") Integer id);

    @Select("select * from ks_order order by created_at desc")
    List<KsOrder> selectKsOrders();

    @Select("select * from ks_order where cellphone=#{cellphone} order by created_at desc")
    List<KsOrder> selectKsOrdersByPhone(@Param("cellphone") String cellphone);

    @Select("select * from ks_order where cellphone like CONCAT(#{searchText},'%') or name like CONCAT(#{searchText},'%') order by created_at desc")
    List<KsOrder> selectKsOrdersBySearchText(@Param("searchText") String searchText);

    @Update("update ks_order set status=#{status}, comment=#{comment} " +
            "where id=#{id}")
    int updateComment(
            @Param("id") Integer id,
            @Param("status") Integer status,
            @Param("comment") String comment
    );

    @Update("update ks_order set status=#{status} " +
            "where id=#{id}")
    int updateStatus(
            @Param("id") Integer id,
            @Param("status") Integer status
    );

    @Update("update ks_order set status=#{status}, pay_channel=#{payChannel}, pay_account=#{payAccount} , payer_name=#{payerName} " +
            "where id=#{id}")
    int updatePaid(@Param("id") Integer id, @Param("status") int status, @Param("payChannel") String payChannel, @Param("payAccount") String payAccount, @Param("payerName") String payerName);
}
