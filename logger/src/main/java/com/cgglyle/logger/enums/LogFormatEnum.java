package com.cgglyle.logger.enums;

import lombok.Getter;

/**
 * 日志格式规定
 *
 * @author lyle
 * @since 2022/08/16
 */
@Getter
public enum LogFormatEnum {
    /**
     * 开始 (业务日志)
     */
    START("(业务日志)"),
    /**
     * [开始时间]=
     */
    START_TIME(" [开始时间]="),
    /**
     * [模块]=
     */
    MODULE(" [module]="),
    /**
     * [方式]=
     */
    METHOD( " [method]="),
    /**
     * [介绍]=
     */
    EXPLAIN(" [explain]="),
    URL(" [url]="),
    URI(" [uri]="),
    CLASS_NAME(" [className]="),
    /**
     * [入参]=
     */
    ARGS(" [入参]="),
    /**
     * [出参]=
     */
    BODY(" [出参]="),
    TIME(" [耗时]="),
    EXCEPTION(" (异常)"),
    MS("ms"),
    /**
     * [操作者ID]=
     */
    USER_ID(" [操作者ID]=")
    ;
    private final String formName;

    LogFormatEnum(String formName) {
        this.formName = formName;
    }
}
