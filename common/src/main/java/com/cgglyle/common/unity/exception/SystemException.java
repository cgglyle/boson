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
import com.cgglyle.common.unity.status.SystemErrorCode;
import lombok.Getter;

/**
 * 系统异常<p>
 * 自定义异常信息
 *
 * @author lyle
 * @since  2022/08/11
 */
@Getter
public class SystemException extends BaseException{
    private final String errorCode;
    private final String errorMassage;

    public SystemException(String msg){
        super(msg);
        this.errorCode = SystemErrorCode.SYSTEM_ERROR.getCode();
        this.errorMassage = msg;
    }

    public SystemException(StatusCode code){
        super(code.getMsg());
        this.errorCode = code.getCode();
        this.errorMassage = code.getMsg();
    }

    public SystemException(StatusCode code, Throwable cause){
        super(code.getMsg(), cause);
        this.errorCode = code.getCode();
        this.errorMassage = code.getMsg();
    }
}
