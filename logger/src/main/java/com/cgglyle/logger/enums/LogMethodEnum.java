package com.cgglyle.logger.enums;

import lombok.Getter;

/**
 * @author lyle
 * @date 2022/08/13
 */
@Getter
public enum LogMethodEnum {
    SEARCH("查询"),
    DELETED("删除"),
    UPDATE("更新"),
    SAVE("存储")
    ;
    private final String methodName;

    LogMethodEnum(String methodName) {
        this.methodName = methodName;
    }
}
