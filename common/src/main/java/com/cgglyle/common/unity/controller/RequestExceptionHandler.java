package com.cgglyle.common.unity.controller;

import com.cgglyle.common.unity.status.ClientErrorCode;
import com.cgglyle.common.unity.status.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 404异常处理
 *
 * @author lyle
 * @since 2022/08/21
 */
@Slf4j
@RestController
public class RequestExceptionHandler implements ErrorController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping("/error")
    public ResultVo<String> errorPage(){
        log.error(ClientErrorCode.NOT_FOUNT.getMsg());
        return ResultVo.error(ClientErrorCode.NOT_FOUNT);
    }
}
