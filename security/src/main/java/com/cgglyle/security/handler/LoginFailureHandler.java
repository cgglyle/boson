package com.cgglyle.security.handler;

import com.cgglyle.common.unity.status.ClientErrorCode;
import com.cgglyle.common.unity.status.ResultVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录失败处理器
 *
 * @author lyle
 * @since 2022/08/19
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    /**
     * Called when an authentication attempt fails.
     *
     * @param request   the request during which the authentication attempt occurred.
     * @param response  the response.
     * @param exception the exception which was thrown to reject the authentication
     *                  request.
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResultVo<Object> resultVo = null;
        if (exception instanceof UsernameNotFoundException){
            resultVo = ResultVo.error(ClientErrorCode.USERNAME_NOTFOUND);
        }else if (exception instanceof BadCredentialsException){
            resultVo = ResultVo.error(ClientErrorCode.USERNAME_PASSWORD_ERROR);
        }else {
            String message = exception.getMessage();
            resultVo = ResultVo.error(ClientErrorCode.CLIENT_ERROR,message);
        }
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(resultVo));
    }
}
