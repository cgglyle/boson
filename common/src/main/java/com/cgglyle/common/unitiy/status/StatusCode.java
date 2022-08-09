package com.cgglyle.common.unitiy.status;

/**
 * 统一返回值接口
 *
 * @author lyle
 */
public interface StatusCode {
    /**
     * 获取统一返回值
     *
     * @return 返回值
     */
    String getCode();

    /**
     * 获取统一描述信息
     *
     * @return 描述信息
     */
    String getMsg();
}
