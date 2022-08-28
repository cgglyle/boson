package com.cgglyle.common.unity.exception;

import com.cgglyle.common.unity.status.StatusCode;
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
