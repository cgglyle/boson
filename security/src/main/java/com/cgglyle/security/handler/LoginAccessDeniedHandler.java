package com.cgglyle.security.handler;

import com.cgglyle.common.unity.status.ClientErrorCode;
import com.cgglyle.common.unity.status.ResultVo;
import com.cgglyle.logger.enums.LogFormatEnum;
import com.cgglyle.logger.event.AccessDeniedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 无权限控制器
 *
 * @author lyle
 * @since 2022/08/21
 */
@Component
@RequiredArgsConstructor
public class LoginAccessDeniedHandler implements AccessDeniedHandler {
    private final ApplicationContext context;
    /**
     * Handles an access denied failure.
     *
     * @param request               that resulted in an <code>AccessDeniedException</code>
     * @param response              so that the user agent can be advised of the failure
     * @param accessDeniedException that caused the invocation
     * @throws IOException      in the event of an IOException
     * @throws ServletException in the event of a ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResultVo<Object> resultVo = ResultVo.error(ClientErrorCode.FORBIDDEN);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(resultVo));
        Map<String, Object> map = new HashMap<>();
        map.put(LogFormatEnum.IP.getFormName(), request.getRemoteAddr());
        map.put(LogFormatEnum.URI.getFormName(), request.getRequestURI());
        map.put(LogFormatEnum.USER_NAME.getFormName(), request.getRemoteUser());
        map.put(" [请求方式]=", request.getMethod());
        context.publishEvent(new AccessDeniedEvent(map));
    }
}
