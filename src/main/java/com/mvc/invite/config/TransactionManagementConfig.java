package com.mvc.invite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Comments：
 * Author：Jay Chang
 * Create Date：2017/5/10
 * Modified By：
 * Modified Date：
 * Why & What is modified：
 * Version：v1.0
 */
@EnableTransactionManagement
@Configuration
public class TransactionManagementConfig {
    @Bean
    public Object testBean(PlatformTransactionManager platformTransactionManager){
        System.out.println(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
        return new Object();
    }
}
