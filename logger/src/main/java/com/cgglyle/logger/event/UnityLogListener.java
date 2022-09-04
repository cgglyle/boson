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

package com.cgglyle.logger.event;

import com.cgglyle.logger.enums.LogFormat;
import com.cgglyle.logger.enums.LogFormatEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 日志异步监听打印
 *
 * @author lyle
 * @since 2022/08/16
 */
@Slf4j
@Component
public class UnityLogListener {
    @Async
    @Order
    @EventListener(UnityLogEvent.class)
    public void printLog(UnityLogEvent event){
        Map<LogFormat, Object> source = (Map<LogFormat, Object>) event.getSource();
        if (source.containsKey(LogFormatEnum.EXCEPTION)){
            log.error("[操作日志](!异常!) [IP]={} [ID]={} [时间]={} [模块]={} [方式]={} [介绍]={} [URL]={} " +
                            "[URI]={} [类名]={} [入参]={} [出参]={} [异常]={}",
                    source.get(LogFormatEnum.IP),
                    source.get(LogFormatEnum.USER_ID),
                    source.get(LogFormatEnum.START_TIME),
                    source.get(LogFormatEnum.MODULE),
                    source.get(LogFormatEnum.METHOD),
                    source.get(LogFormatEnum.EXPLAIN),
                    source.get(LogFormatEnum.URL),
                    source.get(LogFormatEnum.URI),
                    source.get(LogFormatEnum.CLASS_NAME),
                    source.get(LogFormatEnum.ARGS),
                    source.get(LogFormatEnum.BODY),
                    source.get(LogFormatEnum.EXCEPTION));
        }
        else {
            log.info("[操作日志] [IP]={} [ID]={} [时间]={} [模块]={} [方式]={} [介绍]={} [URL]={} " +
                            "[URI]={} [类名]={} [入参]={} [出参]={} [耗时]={}ms",
                    source.get(LogFormatEnum.IP),
                    source.get(LogFormatEnum.USER_ID),
                    source.get(LogFormatEnum.START_TIME),
                    source.get(LogFormatEnum.MODULE),
                    source.get(LogFormatEnum.METHOD),
                    source.get(LogFormatEnum.EXPLAIN),
                    source.get(LogFormatEnum.URL),
                    source.get(LogFormatEnum.URI),
                    source.get(LogFormatEnum.CLASS_NAME),
                    source.get(LogFormatEnum.ARGS),
                    source.get(LogFormatEnum.BODY),
                    source.get(LogFormatEnum.TIME));
        }
    }
}
