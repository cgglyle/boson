package com.cgglyle.common.unitiy.status;

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
    /**
     * 一级宏观错误类型
     */
    CLIENT_ERROR("A0001", "客户端错误");

    private String code;
    private String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}