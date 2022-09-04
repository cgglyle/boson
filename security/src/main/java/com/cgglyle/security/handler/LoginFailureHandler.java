/*
 * Copyright 2022 Cgglyle
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cgglyle.security.handler;

import com.cgglyle.common.unity.status.ClientErrorCode;
import com.cgglyle.common.unity.status.ResultVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
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
        ResultVo<Object> resultVo;
        if (exception instanceof UsernameNotFoundException){
            resultVo = ResultVo.error(ClientErrorCode.USERNAME_NOTFOUND);
        }else if (exception instanceof BadCredentialsException){
            resultVo = ResultVo.error(ClientErrorCode.USERNAME_PASSWORD_ERROR);
        }else if (exception instanceof LockedException){
            resultVo = ResultVo.error(ClientErrorCode.ACCOUNT_LOCKED);
        }else if (exception instanceof AccountExpiredException){
            resultVo = ResultVo.error(ClientErrorCode.ACCOUNT_EXPIRED);
        }else if (exception instanceof DisabledException){
            resultVo = ResultVo.error(ClientErrorCode.DISABLED);
        } else if (exception instanceof CredentialsExpiredException) {
            resultVo = ResultVo.error(ClientErrorCode.PASSWORD_EXPIRED);
        } else {
            String message = exception.getMessage();
            resultVo = ResultVo.error(ClientErrorCode.CLIENT_ERROR,message);
        }
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(resultVo));
    }
}
