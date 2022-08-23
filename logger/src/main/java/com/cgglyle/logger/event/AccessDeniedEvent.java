package com.cgglyle.logger.event;

import org.springframework.context.ApplicationEvent;

import java.util.Map;

/**
 * @author lyle
 * @since 2022/08/21
 */
public class AccessDeniedEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent} with its {@link #getTimestamp() timestamp}
     * set to {@link System#currentTimeMillis()}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public AccessDeniedEvent(Map<String, Object> source) {
        super(source);
    }
}
