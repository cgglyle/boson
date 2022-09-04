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

package com.cgglyle.logger.event;

import com.cgglyle.common.model.UserInfo;
import com.cgglyle.common.unity.status.ClientErrorCode;
import com.cgglyle.logger.enums.LogFormat;
import com.cgglyle.logger.enums.LogFormatEnum;
import com.cgglyle.logger.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 登录登出日志监听器
 *
 * @author lyle
 * @since 2022/08/20
 */
@Slf4j
@Component
public class LoginAndLogoutListener {

    @Async
    @Order
    @EventListener(AuthenticationSuccessEvent.class)
    public void printLog(AuthenticationSuccessEvent event) {
        Authentication authentication = event.getAuthentication();
        Object principal = authentication.getPrincipal();
        UserInfo userInfo = (UserInfo) principal;
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        log.info("[登录日志] [IP]={} [用户ID]={} [用户名]={} [登录时间]={}",
                details.getRemoteAddress(),
                userInfo.getUserId(),
                userInfo.getUsername(),
                DateUtils.timestampToDate(event.getTimestamp()));
    }

    @Async
    @Order
    @EventListener(AbstractAuthenticationFailureEvent.class)
    public void printLog(AbstractAuthenticationFailureEvent event) {
        String message;
        if (event instanceof AuthenticationFailureBadCredentialsEvent) {
            message = ClientErrorCode.USERNAME_PASSWORD_ERROR.getMsg();
        } else if (event instanceof AuthenticationFailureDisabledEvent) {
            message = ClientErrorCode.DISABLED.getMsg();
        } else if (event instanceof AuthenticationFailureExpiredEvent) {
            message = ClientErrorCode.ACCOUNT_EXPIRED.getMsg();
        } else if (event instanceof AuthenticationFailureLockedEvent) {
            message = ClientErrorCode.ACCOUNT_LOCKED.getMsg();
        } else if (event instanceof AuthenticationFailureCredentialsExpiredEvent) {
            message = ClientErrorCode.PASSWORD_EXPIRED.getMsg();
        } else if (event instanceof AuthenticationFailureServiceExceptionEvent) {
            message = "验证系统异常";
        } else {
            message = "未知异常";
        }
        UsernamePasswordAuthenticationToken source = (UsernamePasswordAuthenticationToken) event.getSource();
        WebAuthenticationDetails details = (WebAuthenticationDetails) source.getDetails();
        log.info("[登录日志](!错误!) [IP]={} [尝试登录用户名]={} [错误原因]={} [登录时间]={}",
                details.getRemoteAddress(),
                source.getName(),
                message,
                DateUtils.timestampToDate(event.getTimestamp()));
    }

    @Async
    @Order
    @EventListener(LogoutSuccessEvent.class)
    public void printLog(LogoutSuccessEvent event) {
        Authentication authentication = event.getAuthentication();
        Object principal = authentication.getPrincipal();
        UserInfo userInfo = (UserInfo) principal;
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        log.info("[登出日志] [IP]={} [用户ID]={} [用户名]={} [登录时间]={}",
                details.getRemoteAddress(),
                userInfo.getUserId(),
                userInfo.getUsername(),
                DateUtils.timestampToDate(event.getTimestamp()));
    }

    @Async
    @Order
    @EventListener(AccessDeniedEvent.class)
    public void printLog(AccessDeniedEvent event) {
        Map<LogFormat, Object> source = (Map<LogFormat, Object>) event.getSource();
        log.info("[业务日志](!越权!) [IP]={} [用户名]={} [请求方式]={} [URI]={} [越权原因]={} [操作时间]={}",
                source.get(LogFormatEnum.IP),
                source.get(LogFormatEnum.USER_NAME),
                source.get(LogFormatEnum.METHOD),
                source.get(LogFormatEnum.URI),
                ClientErrorCode.FORBIDDEN.getMsg(),
                DateUtils.timestampToDate(event.getTimestamp())
        );
    }
}
