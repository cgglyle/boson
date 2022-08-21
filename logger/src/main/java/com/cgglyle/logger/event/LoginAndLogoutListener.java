package com.cgglyle.logger.event;

import com.cgglyle.common.model.UserInfo;
import com.cgglyle.common.unity.status.ClientErrorCode;
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
        log.info("(登录日志)" +
                LogFormatEnum.IP.getFormName() + details.getRemoteAddress() +
                LogFormatEnum.USER_ID.getFormName() + userInfo.getUserId() +
                LogFormatEnum.USER_NAME.getFormName() + userInfo.getUsername() +
                LogFormatEnum.OPERATION_TIME.getFormName() + DateUtils.timestampToDate(event.getTimestamp()));
    }

    @Async
    @Order
    @EventListener(AbstractAuthenticationFailureEvent.class)
    public void printLog(AbstractAuthenticationFailureEvent event) {
        String message;
        if (event instanceof AuthenticationFailureBadCredentialsEvent){
            message = ClientErrorCode.USERNAME_PASSWORD_ERROR.getMsg();
        } else if (event instanceof AuthenticationFailureDisabledEvent){
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
        String name = source.getName();
        WebAuthenticationDetails details = (WebAuthenticationDetails) source.getDetails();
        log.info("(登录日志)" +
                LogFormatEnum.IP.getFormName() + details.getRemoteAddress() +
                LogFormatEnum.USER_NAME.getFormName() + name +
                LogFormatEnum.ERROR.getFormName() + message +
                LogFormatEnum.OPERATION_TIME.getFormName() + DateUtils.timestampToDate(event.getTimestamp()));
    }

    @Async
    @Order
    @EventListener(LogoutSuccessEvent.class)
    public void printLog(LogoutSuccessEvent event) {
        Authentication authentication = event.getAuthentication();
        Object principal = authentication.getPrincipal();
        UserInfo userInfo = (UserInfo) principal;
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        log.info("(登出日志)" +
                LogFormatEnum.IP.getFormName() + details.getRemoteAddress() +
                LogFormatEnum.USER_ID.getFormName() + userInfo.getUserId() +
                LogFormatEnum.USER_NAME.getFormName() + userInfo.getUsername() +
                LogFormatEnum.OPERATION_TIME.getFormName() + DateUtils.timestampToDate(event.getTimestamp()));
    }

    @Async
    @Order
    @EventListener(AccessDeniedEvent.class)
    public void printLog(AccessDeniedEvent event) {
        Map<String, Object> source = (Map<String, Object>) event.getSource();
        log.info("(无权限日志)" +
                LogFormatEnum.IP.getFormName() + source.get(LogFormatEnum.IP.getFormName()) +
                LogFormatEnum.USER_NAME.getFormName() + source.get(LogFormatEnum.USER_NAME.getFormName()) +
                " [请求方式]=" + source.get(" [请求方式]=") +
                LogFormatEnum.URI.getFormName() + source.get(LogFormatEnum.URI.getFormName()) +
                LogFormatEnum.ERROR.getFormName() + ClientErrorCode.FORBIDDEN.getMsg() +
                LogFormatEnum.OPERATION_TIME.getFormName() + DateUtils.timestampToDate(event.getTimestamp()));
    }
}
