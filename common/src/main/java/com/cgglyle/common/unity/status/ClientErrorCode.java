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
 * @date 2022/08/11
 */
@Getter
public enum ClientErrorCode implements StatusCode {
    /**
     * 客户端错误
     */
    CLIENT_ERROR("C0001", "客户端错误"),
    NOT_FOUNT("C0002","资源不存在"),
    LOGIN_REQUIRED("C0010","需要登录"),
    /**
     * 用户名或者密码错误
     */
    USERNAME_PASSWORD_ERROR("C0011", "用户名或密码错误"),
    PASSWORD_ERROR("C0012","密码错误"),
    USERNAME_NOTFOUND("C0013", "用户名不存在"),
    /**
     * 数据校验错误
     */
    DATA_ERROR("C0020", "数据错误"),
    DISABLED("C0030", "被禁用"),
    ACCOUNT_EXPIRED("C0014","帐号已过期"),
    PASSWORD_EXPIRED("C0015", "密码已过期"),
    ACCOUNT_LOCKED("C0016", "帐号被锁定"),
    FORBIDDEN("C0017", "无权限")
    ;
    private final String code;
    private final String msg;

    ClientErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
