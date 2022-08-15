package com.cgglyle.logger.aop;

import com.cgglyle.logger.annotaion.UnityLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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
public class UnityLogAspect {
    private static final String START = "(业务日志)";
    private static final String MODULE = " [module]=";
    private static final String METHOD = " [method]=";
    private static final String EXPLAIN = " [explain]=";
    private static final String URL = " [url]=";
    private static final String URI = " [uri]=";
    private static final String CLASS_NAME = " [className]=";
    private static final String ARGS = " [入参]=";
    private static final String BODY = " [出参]=";
    private static final String TIME = " [耗时]=";
    private static final String EXCEPTION = " (异常)";
    private static final String MS = "ms";

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
        Map<String, Object> context = LogContext.getContext();
        context.put(BODY, body);
    }

    /**
     * 含参数验证环绕切入
     */
    @Around(value = "unityLogCut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object body = proceedingJoinPoint.proceed();
        long takeTime = System.currentTimeMillis() - startTime;
        Map<String, Object> context = LogContext.getContext();
        log.info(START + MODULE + context.get(MODULE) + METHOD + context.get(METHOD) +
                EXPLAIN + context.get(EXPLAIN) + URL + context.get(URL) + URI + context.get(URI) +
                CLASS_NAME + context.get(CLASS_NAME) + ARGS + context.get(ARGS) +
                BODY + context.get(BODY) + TIME + takeTime + MS);
        return body;
    }

    /**
     * 异常处理
     */
    @AfterThrowing(value = "unityExceptionLogCut()", throwing = "throwable")
    public void doAfterThrowing(Throwable throwable) {
        Map<String, Object> context = LogContext.getContext();
        log.error(START + EXCEPTION + MODULE + context.get(MODULE) + METHOD + context.get(METHOD) +
                EXPLAIN + context.get(EXPLAIN) + URL + context.get(URL) + URI + context.get(URI) +
                CLASS_NAME + context.get(CLASS_NAME) + ARGS + context.get(ARGS) +
                BODY + context.get(BODY) + EXCEPTION + throwable);
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
        Map<String, Object> logContext = new HashMap<>();
        logContext.put(MODULE, unityLog.module());
        logContext.put(METHOD, unityLog.method().getMethodName());
        logContext.put(EXPLAIN, unityLog.explain());
        logContext.put(URL, httpServletRequest.getRequestURL());
        logContext.put(URI, httpServletRequest.getRequestURI());
        logContext.put(CLASS_NAME, joinPoint.getSignature().getDeclaringTypeName() + "." +
                joinPoint.getSignature().getName());
        // 移除数据验证信息
        List<Object> objects = Arrays.asList(joinPoint.getArgs());
        List<Object> list = new ArrayList<>(objects);
        list.removeIf(m->m.getClass().equals(BeanPropertyBindingResult.class));
        logContext.put(ARGS, Arrays.toString(list.toArray()));
        //TODO: 添加请求用户ID
        new LogContext(logContext);
    }
}
