package com.cgglyle.common.unity.status;

import lombok.Getter;

/**
 * @author lyle
 * @since  2022/08/11
 */
@Getter
public enum SystemErrorCode implements StatusCode{
    /**
     * 系统错误
     */
    SYSTEM_ERROR("S0001", "系统错误"),
    /**
     * 系统正在尝试除以零
     */
    DIVISION_ZERO_ERROR("S0011", "尝试除以零"),
    NULL_POINTER_ERROR("S0012", "出现空指针异常"),
    REPEAT_INITIALIZATION_ERROR("S0002","重复初始化系统")
    ;
    private final String code;
    private final String msg;

    SystemErrorCode(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
