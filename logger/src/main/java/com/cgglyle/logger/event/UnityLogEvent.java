package com.cgglyle.logger.event;

import com.cgglyle.logger.enums.LogFormat;
import org.springframework.context.ApplicationEvent;

import java.util.Map;

/**
 * 事件载体
 *
 * @author lyle
 * @since 2022/08/16
 */
public class UnityLogEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent} with its {@link #getTimestamp() timestamp}
     * set to {@link System#currentTimeMillis()}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public UnityLogEvent(Map<LogFormat, Object> source) {
        super(source);
    }
}
