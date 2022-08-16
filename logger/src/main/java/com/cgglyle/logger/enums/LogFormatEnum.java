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
    START("(业务日志)"),
    MODULE(" [module]="),
    METHOD( " [method]="),
    EXPLAIN(" [explain]="),
    URL(" [url]="),
    URI(" [uri]="),
    CLASS_NAME(" [className]="),
    ARGS(" [入参]="),
    BODY(" [出参]="),
    TIME(" [耗时]="),
    EXCEPTION(" (异常)"),
    MS("ms")
    ;
    private final String formName;

    LogFormatEnum(String formName) {
        this.formName = formName;
    }
}
