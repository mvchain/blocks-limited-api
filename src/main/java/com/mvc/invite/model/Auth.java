package com.mvc.invite.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by ywd-pc on 2017/12/8.
 */
@Getter
@Setter
@ToString
public class Auth {

    public static final String EMP = "EMP";
    public static final String TECH = "TECH";

    private String type;
    private String password;

    public Auth(String type, String password) {
        this.type = type;
        this.password = password;
    }
}
