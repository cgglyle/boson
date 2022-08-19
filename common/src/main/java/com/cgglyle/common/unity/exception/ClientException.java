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
