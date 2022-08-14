package com.cgglyle.security.controller;

import com.cgglyle.logger.annotaion.UnityLog;
import com.cgglyle.logger.enums.LogMethodEnum;
import com.cgglyle.logger.enums.LogModuleEnum;
import com.cgglyle.security.model.RoleEntity;
import com.cgglyle.security.query.RoleSaveQuery;
import com.cgglyle.security.service.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author lyle
 * @date 2022/08/13
 */
@Tag(name = "role")
@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "/role")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService IRoleService;

    @UnityLog(module = LogModuleEnum.SECURITY_ROLE, method = LogMethodEnum.SEARCH, explain = "查找角色")
    @Operation(summary = "查看角色")
    @GetMapping("/role")
    public List<RoleEntity> list() {
        return IRoleService.list();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @UnityLog(module = LogModuleEnum.SECURITY_ROLE, method = LogMethodEnum.SAVE, explain = "添加角色")
    @Operation(summary = "添加角色")
    @PostMapping("/role")
    public boolean save(@RequestBody @Valid RoleSaveQuery vo, BindingResult bindingResult) {
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(vo, roleEntity);
        return IRoleService.save(roleEntity);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @UnityLog(module = LogModuleEnum.SECURITY_ROLE, method = LogMethodEnum.DELETED, explain = "删除角色")
    @Operation(summary = "删除角色")
    @DeleteMapping("/role")
    public boolean remove(Long id) {
        return IRoleService.removeById(id);
    }

    @UnityLog(module = LogModuleEnum.SECURITY_ROLE, method = LogMethodEnum.SEARCH, explain = "查询总数")
    @Operation(summary = "查询总数")
    @GetMapping("/count")
    public long count() {
        return IRoleService.count();
    }
}