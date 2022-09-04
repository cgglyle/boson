package com.cgglyle.logger.enums;

import lombok.Getter;

/**
 * 日志格式规定
 *
 * @author lyle
 * @since 2022/08/16
 */
@Getter
public enum LogFormatEnum implements LogFormat{
    /**
     * 开始时间
     */
    START_TIME,
    /**
     * 模块
     */
    MODULE,
    /**
     * 方式
     */
    METHOD,
    /**
     * 介绍
     */
    EXPLAIN,
    URL,
    URI,
    CLASS_NAME,
    /**
     * 入参
     */
    ARGS,
    /**
     * 出参
     */
    BODY,
    TIME,
    EXCEPTION,
    USER_ID,
    USER_NAME,
    OPERATION_TIME,
    IP,
    ERROR
}
