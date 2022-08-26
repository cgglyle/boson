package com.cgglyle.common.unity.exception;

import com.cgglyle.common.unity.status.ClientErrorCode;
import com.cgglyle.common.unity.status.ResultVo;
import com.cgglyle.common.unity.status.SystemErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Objects;

/**
 * 统一异常处理控制器
 *
 * @author lyle
 */
@Slf4j
@RestControllerAdvice
public class UnityExceptionHandler {

    /**
     * 未统计的异常
     * <p>
     * 没有返回枚举类中的异常信息
     */

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVo<String> exceptionHandler(Exception e){
        log.error("未定义异常", e);
        return ResultVo.error(SystemErrorCode.SYSTEM_ERROR);
    }

    /**
     * 系统 —— 算数异常
     * <p>
     * 除以零异常
     */
    @ExceptionHandler(value = ArithmeticException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVo<String> exceptionHandler(ArithmeticException e){
        log.error(SystemErrorCode.DIVISION_ZERO_ERROR.getMsg(), e);
        return ResultVo.error(SystemErrorCode.DIVISION_ZERO_ERROR);
    }

    /**
     * 客户端异常
     *
     * @param e {@link ClientException} 自定义客户端异常
     * @return 返回错误代码，错误信息，不包含数据
     */
    @ExceptionHandler(value = ClientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVo<String> exceptionHandler(ClientException e) {
        log.error(ClientErrorCode.USERNAME_PASSWORD_ERROR.getMsg(), e);
        return ResultVo.error(e.getErrorCode(), e.getErrorMassage());
    }

    /**
     * 数据异常
     * <p>
     * 各种数据有效性异常
     *
     * @return 返回错误代码，错误信息，错误位置
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVo<String> exceptionHandler(BindException e) {
        log.error(ClientErrorCode.DATA_ERROR.getMsg(), e);
        return ResultVo.error(ClientErrorCode.DATA_ERROR,
                Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    /**
     * 数据异常
     * <p>
     * 各种数据有效性异常
     *
     * @return 返回错误代码，错误信息，错误位置
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVo<String> exceptionHandler(ConstraintViolationException e) {
        log.error(ClientErrorCode.DATA_ERROR.getMsg(), e);
        return ResultVo.error(ClientErrorCode.DATA_ERROR, e);
    }
}
