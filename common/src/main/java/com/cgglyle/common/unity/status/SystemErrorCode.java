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
