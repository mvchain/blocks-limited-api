package com.mvc.invite.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by ywd-pc on 2017/12/8.
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
