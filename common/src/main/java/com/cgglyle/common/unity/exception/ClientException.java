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

package com.cgglyle.common.unity.exception;

import com.cgglyle.common.unity.status.StatusCode;
import lombok.Getter;

/**
 * 自定义客户端异常
 *
 * @author lyle
 * @date 2022/08/11
 */
@Getter
public class ClientException extends BaseException{
    private final String errorCode;
    private final String errorMassage;

    /**
     * 构造客户端异常
     * @param code 异常代码
     */
    public ClientException(StatusCode code){
        super(code.getMsg());
        this.errorCode = code.getCode();
        this.errorMassage = code.getMsg();
    }

    /**
     * 构造客户端异常
     * @param code 异常代码
     * @param cause 异常原因
     */
    public ClientException(StatusCode code, Throwable cause){
        super(code.getMsg(), cause);
        this.errorCode = code.getCode();
        this.errorMassage = code.getMsg();
    }
}
