package com.cgglyle.common.unity.status;

import lombok.Getter;

/**
 * @author lyle
 * @date 2022/08/11
 */
@Getter
public enum ClientErrorCode implements StatusCode{
    /**
     * 客户端错误
     */
    CLIENT_ERROR("C0001", "客户端错误"),
    /**
     * 用户名或者密码错误
     */
    USERNAME_PASSWORD_ERROR("C00011", "用户名或密码错误");
    private final String code;
    private final String msg;

    ClientErrorCode(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
