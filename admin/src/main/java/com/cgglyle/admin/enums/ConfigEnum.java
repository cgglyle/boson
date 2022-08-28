package com.cgglyle.admin.enums;

import lombok.Getter;

/**
 * 配置信息枚举
 *
 * @author lyle
 * @since 2022/08/28
 */
@Getter
public enum ConfigEnum implements ConfigCode {
    IS_ANONYMOUS_USER("IS_ANONYMOUS_USER"),
    IS_INITIALIZATION_ADMIN("IS_INITIALIZATION_ADMIN"),
    IS_INITIALIZATION_PERMISSION("IS_INITIALIZATION_PERMISSION"),
    IS_INITIALIZATION_CONFIG("IS_INITIALIZATION_CONFIG");

    private final String msg;

    ConfigEnum(String msg) {
        this.msg = msg;
    }
}
