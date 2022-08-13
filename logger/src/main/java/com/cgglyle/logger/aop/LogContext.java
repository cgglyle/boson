package com.cgglyle.logger.aop;

import java.util.Map;

/**
 * 线程日志上下文
 *
 * @author lyle
 * @date 2022/08/13
 */
public class LogContext implements AutoCloseable {
    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<>();

    public LogContext(Map<String, Object> context) {
        THREAD_LOCAL.set(context);
    }

    public static Map<String, Object> getContext() {
        return THREAD_LOCAL.get();
    }

    @Override
    public void close() throws Exception {
        THREAD_LOCAL.remove();
    }
}
