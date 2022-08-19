package com.cgglyle.common.unity.status;

import lombok.Getter;

/**
 * 统一返回值枚举类
 *
 * @author lyle
 */
@Getter
public enum ResultCode implements StatusCode {
    /**
     * 一切OK
     */
    SUCCESS("00000", "请求成功"),
    LOGIN_SUCCESS("00000", "登录成功"),
    LOGOUT_SUCCESS("00000", "注销成功");

    private final String code;
    private final String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
