package com.cgglyle.logger.enums;

import lombok.Getter;

/**
 * @author lyle
 * @date 2022/08/13
 */
@Getter
public enum LogModuleEnum {
    SECURITY_USER("安全-用户模块"),
    SECURITY_ROLE("安全-角色模块"),
    SECURITY_PERMISSION("安全-权限模块")
    ;
    private final String moduleName;

    LogModuleEnum(String moduleName) {
        this.moduleName = moduleName;
    }
}
