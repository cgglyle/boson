/*
 * Copyright 2022 Cgglyle
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
    SECURITY_USER_ROLE_RELATION("安全-用户角色关系模块"),
    SECURITY_PERMISSION("安全-权限模块")
    ;
    private final String moduleName;

    LogModuleEnum(String moduleName) {
        this.moduleName = moduleName;
    }
}
