package com.cgglyle.common.unity.status;

import lombok.Data;

/**
 * 返回包装类
 *
 * @author lyle
 */
@Data
public class ResultVo<T> {
    private String code;
    private String msg;
    private T data;

    /**
     * 手动设置返回vo
     */
    private ResultVo(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 默认成功返回vo
     */
    private ResultVo(T data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }

    /**
     * 返回指定状态码，数据对象
     */
    private ResultVo(StatusCode statusCode, T data) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = data;
    }

    /**
     * 返回指定状态码，指定原因
     * @param statusCode 状态码
     * @param cause 原因
     */
    private ResultVo(StatusCode statusCode, String cause) {
        this.code = statusCode.getCode();
        this.msg = cause;
        this.data = null;
    }

    /**
     * 只返回状态码
     */
    private ResultVo(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = null;
    }

    /**
     * 成功
     *
     * @param data 成功信息
     * @return 返回体
     * @param <T> 返回体类型
     */
    public static <T> ResultVo<T> success(T data){
        return new ResultVo<>(data);
    }

    /**
     * 成功
     * 不带数据
     *
     * @param code 成功状态值
     * @return 返回体
     * @param <T> 返回体类型
     */
    public static <T> ResultVo<T> success(StatusCode code){
        return new ResultVo<>(code);
    }

    /**
     * 成功
     *
     * @param code 成功状态值
     * @param data 数据
     * @return 返回体
     * @param <T> 返回体类型
     */
    public static <T> ResultVo<T> success(StatusCode code, T data){
        return new ResultVo<>(code, data);
    }

    /**
     * 错误
     *
     * @param code 错误代码
     * @return 返回体
     * @param <T> 返回体类型
     */
    public static <T> ResultVo<T> error(StatusCode code){
        return new ResultVo<>(code);
    }

    /**
     * 错误(含堆栈错误信息）
     *
     * @param code 错误代码
     * @param cause 堆栈错误原因
     * @return 返回体
     * @param <T> 返回体类型
     */
    public static <T> ResultVo<T> error(StatusCode code, Throwable cause) {
        return new ResultVo<>(code, (T) cause.getMessage());
    }

    public static <T> ResultVo<T> error(StatusCode code, String cause) {
        return new ResultVo<>(code, cause);
    }

    /**
     * 错误
     *
     * @param code 错误代码
     * @param msg  错误信息
     * @param <T>  返回体类型
     * @return 返回体
     */
    public static <T> ResultVo<T> error(String code, String msg) {
        return new ResultVo<>(code, msg, null);
    }

    /**
     * 手动设置返回值
     * <p>
     * <B>尽量不要使用这个，请在{@link ResultCode}中添加枚举信息</B>
     * <B>或者创建一个继承{@link StatusCode}接口的新枚举类</B>
     *
     * @param code 返回值
     * @param msg 返回信息
     * @param data 数据
     * @return 返回体
     * @param <T> 返回体类型
     * @deprecated
     */
    @Deprecated
    public static <T> ResultVo<T> temp(String code, String msg, T data){
        return new ResultVo<>(code, msg, data);
    }
}
