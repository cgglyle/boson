package com.cgglyle.admin.controller;

import com.cgglyle.admin.query.InitAdminSaveQuery;
import com.cgglyle.admin.service.IInitializationService;
import com.cgglyle.logger.annotaion.UnityLog;
import com.cgglyle.logger.enums.LogMethodEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 初始化控制器
 *
 * @author lyle
 * @since 2022/08/28
 */
@Tag(name = "初始化控制器")
@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "/initialization")
@RequiredArgsConstructor
public class InitializationController {
    private static final String MODULE = "系统初始化模块";
    private final IInitializationService iInitializationService;

    @ResponseStatus(HttpStatus.CREATED)
    @UnityLog(module = MODULE, method = LogMethodEnum.SAVE, explain = "初始化系统")
    @Operation(summary = "初始化系统", description = "必须首先调用初始化系统接口，才能进行其他的操作！")
    @PostMapping("/system")
    public boolean system(@RequestBody @Valid InitAdminSaveQuery query, BindingResult bindingResult) {
        return iInitializationService.initSystem(query);
    }

}
