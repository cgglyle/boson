package com.cgglyle.common.unitiy.status;

import lombok.Data;

/**
 * 返回包装类
 *
 * @author lyle
 */
@Data
public class ResultVo {
    private String code;
    private String msg;
    private Object data;

    /**
     * 手动设置返回vo
     */
    public ResultVo(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 默认成功返回vo
     */
    public ResultVo(Object data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }

    /**
     * 返回指定状态码，数据对象
     */
    public ResultVo(StatusCode statusCode, Object data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }

    /**
     * 只返回状态码
     */
    public ResultVo(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = null;
    }
}
