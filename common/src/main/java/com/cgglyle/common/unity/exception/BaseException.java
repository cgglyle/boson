package com.cgglyle.common.unity.exception;

/**
 * 异常基础类
 * </p>
 * 所有异常类都需要集成此类
 *
 * @author lyle
 */
public class BaseException extends RuntimeException{
    public BaseException(){}

    public BaseException(String message){
        super(message);
    }

    public BaseException(Throwable cause){
        super(cause);
    }

    public BaseException(String message, Throwable cause){
        super(message, cause);
    }
}
