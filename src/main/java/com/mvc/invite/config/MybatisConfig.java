package com.mvc.invite.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by ywd-pc on 2017/12/8.
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.mvc.invite.mapper"})
public class MybatisConfig {
}
