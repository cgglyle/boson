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
