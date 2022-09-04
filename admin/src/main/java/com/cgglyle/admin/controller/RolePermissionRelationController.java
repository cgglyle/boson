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

package com.cgglyle.admin.controller;

import com.cgglyle.admin.model.entity.RolePermissionRelationEntity;
import com.cgglyle.admin.query.RolePermissionRelationSaveQuery;
import com.cgglyle.admin.service.IRolePermissionRelationService;
import com.cgglyle.admin.vo.RoleInheritanceVo;
import com.cgglyle.logger.annotaion.UnityLog;
import com.cgglyle.logger.enums.LogMethodEnum;
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
 * 角色权限关联表 前端控制器
 * </p>
 *
 * @author lyle
 * @since 2022-08-21
 */
@Tag(name = "角色权限控制", description = "角色权限控制器")
@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "/admin/role/")
@RequiredArgsConstructor
public class RolePermissionRelationController {

    private final IRolePermissionRelationService rolePermissionRelationService;
    private static final String MODULE = "安全-角色权限关联";


    @UnityLog(module = MODULE, method = LogMethodEnum.SEARCH, explain = "查看全部角色权限关联")
    @Operation(summary = "查看全部角色权限关联")
    @GetMapping("/permission")
    public List<RolePermissionRelationEntity> list() {
        return rolePermissionRelationService.list();
    }

    @UnityLog(module = MODULE, method = LogMethodEnum.SEARCH, explain = "根据ID查找角色权限关联")
    @Operation(summary = "根据ID查找角色权限关联")
    @GetMapping("/permission/{id}")
    public RoleInheritanceVo getById(@PathVariable(value = "id") Long id){
        RoleInheritanceVo roleInheritanceVo = new RoleInheritanceVo();
        BeanUtils.copyProperties(rolePermissionRelationService.getById(id),roleInheritanceVo);
        return roleInheritanceVo;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @UnityLog(module = MODULE, method = LogMethodEnum.SAVE, explain = "添加角色权限关联")
    @Operation(summary = "添加角色权限关联")
    @PostMapping("/permission")
    public boolean save(@RequestBody @Valid RolePermissionRelationSaveQuery query, BindingResult bindingResult) {
        RolePermissionRelationEntity rolePermissionRelationEntity = new RolePermissionRelationEntity();
        BeanUtils.copyProperties(query, rolePermissionRelationEntity);
        return rolePermissionRelationService.save(rolePermissionRelationEntity);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @UnityLog(module = MODULE, method = LogMethodEnum.DELETED, explain = "删除角色权限关联")
    @Operation(summary = "删除角色权限关联")
    @DeleteMapping("/permission")
    public void remove(Long id) {
        rolePermissionRelationService.removeById(id);
    }

    @UnityLog(module = MODULE, method = LogMethodEnum.SEARCH, explain = "查询总数")
    @Operation(summary = "查询总数")
    @GetMapping("/permission/counts")
    public long count() {
        return rolePermissionRelationService.count();
    }


}
