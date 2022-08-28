package com.cgglyle.admin.controller;

import com.cgglyle.admin.model.entity.PermissionEntity;
import com.cgglyle.admin.model.entity.RoleNeoEntity;
import com.cgglyle.admin.query.PermissionSaveQuery;
import com.cgglyle.admin.query.RoleNeoSaveQuery;
import com.cgglyle.admin.service.IPermissionNeoService;
import com.cgglyle.admin.service.IPermissionService;
import com.cgglyle.admin.vo.RoleInheritanceVo;
import com.cgglyle.logger.annotaion.UnityLog;
import com.cgglyle.logger.enums.LogMethodEnum;
import com.cgglyle.security.event.DynamicAuthorizationChangeEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 接口权限表 前端控制器
 * </p>
 *
 * @author lyle
 * @since 2022-08-21
 */
@Tag(name = "权限控制", description = "URL权限控制器")
@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class PermissionController {
    private final IPermissionService permissionService;
    private final ApplicationContext applicationContext;
    private final IPermissionNeoService permissionNeoService;
    private static final String MODULE = "安全-URL权限";


    @UnityLog(module = MODULE, method = LogMethodEnum.SEARCH, explain = "查看全部URL权限")
    @Operation(summary = "查看全部URL权限")
    @GetMapping("/permission")
    public List<PermissionEntity> list() {
        return permissionService.list();
    }

    @UnityLog(module = MODULE, method = LogMethodEnum.SEARCH, explain = "根据ID查找URL权限")
    @Operation(summary = "根据ID查找URL权限")
    @GetMapping("/permission/{id}")
    public RoleInheritanceVo getById(@PathVariable(value = "id") Long id){
        RoleInheritanceVo roleInheritanceVo = new RoleInheritanceVo();
        BeanUtils.copyProperties(permissionService.getById(id),roleInheritanceVo);
        return roleInheritanceVo;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @UnityLog(module = MODULE, method = LogMethodEnum.SAVE, explain = "添加URL权限")
    @Operation(summary = "添加URL权限")
    @PostMapping("/permission")
    public boolean save(@RequestBody @Valid PermissionSaveQuery query, BindingResult bindingResult) {
        PermissionEntity permissionEntity = new PermissionEntity();
        BeanUtils.copyProperties(query, permissionEntity);
        return permissionService.save(permissionEntity);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @UnityLog(module = MODULE, method = LogMethodEnum.DELETED, explain = "删除URL权限")
    @Operation(summary = "删除角色继承")
    @DeleteMapping("/permission")
    public void remove(Long id) {
        permissionService.removeById(id);
    }

    @UnityLog(module = MODULE, method = LogMethodEnum.SEARCH, explain = "查询总数")
    @Operation(summary = "查询总数")
    @GetMapping("/permission/counts")
    public long count() {
        return permissionService.count();
    }

    @UnityLog(module = MODULE, method = LogMethodEnum.SEARCH, explain = "更新变化")
    @Operation(summary = "更新变化")
    @GetMapping("/permission/change")
    public void changeEvent() {
        applicationContext.publishEvent(new DynamicAuthorizationChangeEvent(true));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @UnityLog(module = MODULE, method = LogMethodEnum.SAVE, explain = "NEO添加权限")
    @Operation(summary = "NEO添加权限")
    @PostMapping("/neo/permission")
    public RoleNeoEntity neoSave(@RequestBody @Valid RoleNeoSaveQuery query, BindingResult bindingResult) {
        permissionNeoService.save(null);
        return null;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @UnityLog(module = MODULE, method = LogMethodEnum.SAVE, explain = "NEO添加权限关系")
    @Operation(summary = "NEO添加权限关系")
    @PostMapping("/neo/permission/relationship")
    public void neoSave(@RequestParam @NotNull Long toId, @RequestParam @NotNull Long formId) {
        permissionNeoService.saveRelationship(toId, formId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @UnityLog(module = MODULE, method = LogMethodEnum.DELETED, explain = "删除所有权限")
    @Operation(summary = "删除所有权限",description = "无需开发界面，仅供测试使用")
    @DeleteMapping("/neo/permission/all")
    public void neoRemoveAllPermission() {
        permissionNeoService.deletedAllPermission();
    }
}
