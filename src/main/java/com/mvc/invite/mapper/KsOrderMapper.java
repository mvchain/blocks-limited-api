package com.mvc.invite.mapper;

import com.mvc.invite.model.KsOrder;

import java.util.List;

/**
 * Created by ywd-pc on 2017/12/11.
 */
public interface KsOrderMapper extends BaseMapper<KsOrder> {

    int insertKsOrder();

    List<KsOrder> selectKsOrders();

    List<KsOrder> selectKsOrdersByPhone();
}
