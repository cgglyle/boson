package com.cgglyle.security.handler;

import com.cgglyle.common.unity.status.ResultCode;
import com.cgglyle.common.unity.status.ResultVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 注销处理器
 *
 * @author lyle
 * @since 2022/08/19
 */
@Component
public class LogoutHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=utf-8");
        ResultVo<String> success = ResultVo.success(ResultCode.LOGOUT_SUCCESS);
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(success));
    }
}
