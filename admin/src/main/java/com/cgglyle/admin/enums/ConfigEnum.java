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
