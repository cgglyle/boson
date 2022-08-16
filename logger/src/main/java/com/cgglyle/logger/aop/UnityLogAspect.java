package com.cgglyle.logger.aop;

import com.cgglyle.logger.annotaion.UnityLog;
import com.cgglyle.logger.enums.LogFormatEnum;
import com.cgglyle.logger.event.UnityLogEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 统一日志AOP
 *
 * @author lyle
 * @date 2022/08/13
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class UnityLogAspect {

    private final Map<String, Object> logContext = new HashMap<>();
    private final ApplicationContext applicationContext;

    /**
     * 正常切入点，包含验证
     */
    @Pointcut("within(com.cgglyle.*.controller..*)")
    public void unityLogCut() {
    }

    /**
     * 异常切入点
     */
    @Pointcut("within(com.cgglyle.*.controller..*)")
    public void unityExceptionLogCut() {
    }

    /**
     * 切入前信息处理
     *
     * @param joinPoint 连节点
     */
    @Before(value = "unityLogCut()&&@annotation(unityLog)")
    public void unityLog(JoinPoint joinPoint, UnityLog unityLog) {
        joinContext(joinPoint, unityLog);
    }

    /**
     * 切入后信息处理
     */
    @AfterReturning(value = "unityLogCut()", returning = "body")
    public void doAfterReturning(Object body) {
        logContext.put(LogFormatEnum.BODY.getFormName(), body);
    }

    /**
     * 含参数验证环绕切入
     */
    @Around(value = "unityLogCut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object body = proceedingJoinPoint.proceed();
        long takeTime = System.currentTimeMillis() - startTime;
        logContext.put(LogFormatEnum.TIME.getFormName(), takeTime);
        applicationContext.publishEvent(new UnityLogEvent(logContext));
        return body;
    }

    /**
     * 异常处理
     */
    @AfterThrowing(value = "unityExceptionLogCut()", throwing = "throwable")
    public void doAfterThrowing(Throwable throwable) {
        logContext.put(LogFormatEnum.EXCEPTION.getFormName(), throwable);
        applicationContext.publishEvent(new UnityLogEvent(logContext));
    }

    /**
     * 上下文信息获取
     *
     * @param joinPoint 连节点
     * @param unityLog  日志注解
     */
    private void joinContext(JoinPoint joinPoint, UnityLog unityLog) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest httpServletRequest = (HttpServletRequest) requestAttributes.
                resolveReference(RequestAttributes.REFERENCE_REQUEST);
        assert httpServletRequest != null;
        logContext.clear();
        logContext.put(LogFormatEnum.MODULE.getFormName(), unityLog.module());
        logContext.put(LogFormatEnum.METHOD.getFormName(), unityLog.method().getMethodName());
        logContext.put(LogFormatEnum.EXPLAIN.getFormName(), unityLog.explain());
        logContext.put(LogFormatEnum.URL.getFormName(), httpServletRequest.getRequestURL());
        logContext.put(LogFormatEnum.URI.getFormName(), httpServletRequest.getRequestURI());
        logContext.put(LogFormatEnum.CLASS_NAME.getFormName(), joinPoint.getSignature().getDeclaringTypeName() + "." +
                joinPoint.getSignature().getName());
        // 移除数据验证信息
        List<Object> objects = Arrays.asList(joinPoint.getArgs());
        List<Object> list = new ArrayList<>(objects);
        list.removeIf(m->m.getClass().equals(BeanPropertyBindingResult.class));
        logContext.put(LogFormatEnum.ARGS.getFormName(), Arrays.toString(list.toArray()));
        //TODO: 添加请求用户ID
    }
}
