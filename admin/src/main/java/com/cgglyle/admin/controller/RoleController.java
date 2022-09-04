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

import com.cgglyle.admin.model.entity.RoleEntity;
import com.cgglyle.admin.model.entity.RoleNeoEntity;
import com.cgglyle.admin.query.RoleNeoSaveQuery;
import com.cgglyle.admin.query.RoleSaveQuery;
import com.cgglyle.admin.service.IRoleService;
import com.cgglyle.admin.service.IRoleNeoService;
import com.cgglyle.admin.vo.RoleVo;
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
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * @author lyle
 * @since  2022/08/13
 */
@Tag(name = "角色控制", description = "角色控制器")
@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService IRoleService;

    private final IRoleNeoService IRoleNeoService;

    private static final String SECURITY_ROLE = "安全-角色模块";

    @UnityLog(module = SECURITY_ROLE, method = LogMethodEnum.SEARCH, explain = "查找角色")
    @Operation(summary = "查看角色")
    @GetMapping("/roles")
    public List<RoleEntity> list() {
        return IRoleService.list();
    }

    @UnityLog(module = SECURITY_ROLE, method = LogMethodEnum.SEARCH, explain = "根据ID查找角色")
    @Operation(summary = "根据ID查找角色")
    @GetMapping("/roles/{id}")
    public RoleVo getById(@PathVariable(value = "id") Long id){
        RoleVo roleVo = new RoleVo();
        BeanUtils.copyProperties(IRoleService.getById(id), roleVo);
        return roleVo;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @UnityLog(module = SECURITY_ROLE, method = LogMethodEnum.SAVE, explain = "添加角色")
    @Operation(summary = "添加角色")
    @PostMapping("/roles")
    public boolean save(@RequestBody @Valid RoleSaveQuery vo, BindingResult bindingResult) {
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(vo, roleEntity);
        return IRoleService.save(roleEntity);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @UnityLog(module = SECURITY_ROLE, method = LogMethodEnum.DELETED, explain = "删除角色")
    @Operation(summary = "删除角色")
    @DeleteMapping("/roles")
    public boolean remove(Long id) {
        return IRoleService.removeById(id);
    }

    @UnityLog(module = SECURITY_ROLE, method = LogMethodEnum.SEARCH, explain = "查询总数")
    @Operation(summary = "查询总数")
    @GetMapping("/roles/counts")
    public long count() {
        return IRoleService.count();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @UnityLog(module = SECURITY_ROLE, method = LogMethodEnum.SAVE, explain = "NEO添加角色")
    @Operation(summary = "NEO添加角色")
    @PostMapping("/neo/roles")
    public RoleNeoEntity neoSave(@RequestBody @Valid RoleNeoSaveQuery query, BindingResult bindingResult) {
        RoleNeoEntity roleEntity = new RoleNeoEntity();
        BeanUtils.copyProperties(query, roleEntity);
        return IRoleNeoService.save(roleEntity);
    }

    @UnityLog(module = SECURITY_ROLE, method = LogMethodEnum.SEARCH, explain = "NEO查看角色")
    @Operation(summary = "NEO查看角色")
    @GetMapping("/neo/roles")
    public List<RoleNeoEntity> neoList() {
        return IRoleNeoService.list();
    }

    @UnityLog(module = SECURITY_ROLE, method = LogMethodEnum.SEARCH, explain = "NEO根据ID查找角色")
    @Operation(summary = "NEO根据ID查找角色")
    @GetMapping("/neo/roles/{id}")
    public Optional<RoleNeoEntity> neoGetById(@PathVariable(value = "id") Long id){
        return IRoleNeoService.findById(id);
    }

    @UnityLog(module = SECURITY_ROLE, method = LogMethodEnum.SEARCH, explain = "NEO根据ID查找角色所有父角色")
    @Operation(summary = "NEO根据ID查找角色所有父角色")
    @GetMapping("/neo/roles/{id}/parent")
    public List<RoleNeoEntity> neoGetRoleAllParentById(@PathVariable(value = "id") Long id){
        return IRoleNeoService.getRoleNeoEntityAllParentByRoleId(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @UnityLog(module = SECURITY_ROLE, method = LogMethodEnum.DELETED, explain = "NEO除角色")
    @Operation(summary = "NEO删除角色")
    @DeleteMapping("/neo/roles")
    public void neoRemove(Long id) {
        IRoleNeoService.deletedById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @UnityLog(module = SECURITY_ROLE, method = LogMethodEnum.DELETED, explain = "NEO删除角色关系")
    @Operation(summary = "NEO删除角色关系")
    @DeleteMapping("/neo/roles/relationship")
    public void neoRemoveRelationship(@RequestParam @NotNull Long toId, @RequestParam @NotNull Long formId) {
        IRoleNeoService.deletedRelationship(toId, formId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @UnityLog(module = SECURITY_ROLE, method = LogMethodEnum.SAVE, explain = "NEO添加角色关系")
    @Operation(summary = "NEO添加角色关系")
    @PostMapping("/neo/roles/relationship")
    public void neoSave(@RequestParam @NotNull Long toId, @RequestParam @NotNull Long formId) {
        IRoleNeoService.saveRelationship(toId, formId);
    }
}
