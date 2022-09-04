package com.cgglyle.admin.controller;

import com.cgglyle.admin.model.entity.ConfigEntity;
import com.cgglyle.admin.query.ConfigUpdateQuery;
import com.cgglyle.admin.service.IConfigService;
import com.cgglyle.admin.vo.ConfigVo;
import com.cgglyle.logger.annotaion.UnityLog;
import com.cgglyle.logger.enums.LogMethodEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 安全配置 前端控制器
 * </p>
 *
 * @author lyle
 * @since 2022-08-25
 */
@Tag(name = "安全配置")
@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
public class ConfigController {
    private static final String MODULE = "安全-URL权限";
    private final IConfigService configService;

    @UnityLog(module = MODULE, method = LogMethodEnum.SEARCH, explain = "查看全部配置")
    @Operation(summary = "查看全部配置")
    @GetMapping("/config")
    public List<ConfigEntity> list() {
        return configService.list();
    }

    @UnityLog(module = MODULE, method = LogMethodEnum.SEARCH, explain = "根据ID查找配置")
    @Operation(summary = "根据ID查找配置")
    @GetMapping("/config/{id}")
    public ConfigVo getById(@PathVariable(value = "id") Long id) {
        ConfigVo configVo = new ConfigVo();
        BeanUtils.copyProperties(configService.getById(id), configVo);
        return configVo;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @UnityLog(module = MODULE, method = LogMethodEnum.UPDATE, explain = "更新配置")
    @Operation(summary = "更新配置")
    @PutMapping("/config")
    public void updateConfig(@RequestBody @Valid ConfigUpdateQuery query){
        configService.setValueByKey(query.configKey(), query.configValue());
    }

}
