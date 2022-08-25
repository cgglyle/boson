package com.cgglyle.security.event;

import org.springframework.context.ApplicationEvent;

/**
 * 告知动态权限系统权限已更改
 *
 * @author lyle
 * @since 2022/08/25
 */
public class DynamicAuthorizationChangeEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent} with its {@link #getTimestamp() timestamp}
     * set to {@link System#currentTimeMillis()}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public DynamicAuthorizationChangeEvent(Boolean source) {
        super(source);
    }
}
