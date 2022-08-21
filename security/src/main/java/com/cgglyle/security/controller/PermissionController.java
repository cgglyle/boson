package com.cgglyle.security.controller;

import com.cgglyle.logger.annotaion.UnityLog;
import com.cgglyle.logger.enums.LogMethodEnum;
import com.cgglyle.security.model.entity.PermissionEntity;
import com.cgglyle.security.query.PermissionSaveQuery;
import com.cgglyle.security.service.IPermissionService;
import com.cgglyle.security.vo.RoleInheritanceVo;
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
 * <p>
 * 接口权限表 前端控制器
 * </p>
 *
 * @author lyle
 * @since 2022-08-21
 */
@Tag(name = "Permission", description = "URL权限控制器")
@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class PermissionController {
    private final IPermissionService permissionService;
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

}
