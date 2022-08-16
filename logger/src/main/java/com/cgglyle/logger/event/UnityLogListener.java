package com.cgglyle.logger.event;

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
        Map<String, Object> source = (Map<String, Object>) event.getSource();
        if (source.containsKey(LogFormatEnum.EXCEPTION.getFormName())){
            log.error(LogFormatEnum.START.getFormName() + LogFormatEnum.EXCEPTION.getFormName() +
                    LogFormatEnum.MODULE.getFormName() + source.get(LogFormatEnum.MODULE.getFormName()) +
                    LogFormatEnum.METHOD.getFormName() + source.get(LogFormatEnum.METHOD.getFormName()) +
                    LogFormatEnum.EXPLAIN.getFormName() + source.get(LogFormatEnum.EXPLAIN.getFormName()) +
                    LogFormatEnum.URL.getFormName() + source.get(LogFormatEnum.URL.getFormName()) +
                    LogFormatEnum.URI.getFormName() + source.get(LogFormatEnum.URI.getFormName()) +
                    LogFormatEnum.CLASS_NAME.getFormName() + source.get(LogFormatEnum.CLASS_NAME.getFormName()) +
                    LogFormatEnum.ARGS.getFormName() + source.get(LogFormatEnum.ARGS.getFormName()) +
                    LogFormatEnum.BODY.getFormName() + source.get(LogFormatEnum.BODY.getFormName()) +
                    LogFormatEnum.EXCEPTION.getFormName() + source.get(LogFormatEnum.EXCEPTION.getFormName()));
        }
        else {
            log.info(LogFormatEnum.START.getFormName() +
                LogFormatEnum.MODULE.getFormName() + source.get(LogFormatEnum.MODULE.getFormName()) +
                LogFormatEnum.METHOD.getFormName() + source.get(LogFormatEnum.METHOD.getFormName()) +
                LogFormatEnum.EXPLAIN.getFormName() + source.get(LogFormatEnum.EXPLAIN.getFormName()) +
                LogFormatEnum.URL.getFormName() + source.get(LogFormatEnum.URL.getFormName()) +
                LogFormatEnum.URI.getFormName() + source.get(LogFormatEnum.URI.getFormName()) +
                LogFormatEnum.CLASS_NAME.getFormName() + source.get(LogFormatEnum.CLASS_NAME.getFormName()) +
                LogFormatEnum.ARGS.getFormName() + source.get(LogFormatEnum.ARGS.getFormName()) +
                LogFormatEnum.BODY.getFormName() + source.get(LogFormatEnum.BODY.getFormName()) +
                LogFormatEnum.TIME.getFormName() + source.get(LogFormatEnum.TIME.getFormName()) +
                LogFormatEnum.MS.getFormName()
            );
        }
    }
}
