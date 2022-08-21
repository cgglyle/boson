package com.cgglyle.security.controller;

import com.cgglyle.logger.annotaion.UnityLog;
import com.cgglyle.logger.enums.LogMethodEnum;
import com.cgglyle.security.model.entity.RoleEntity;
import com.cgglyle.security.query.RoleSaveQuery;
import com.cgglyle.security.service.IRoleService;
import com.cgglyle.security.vo.RoleVo;
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
@Tag(name = "Role", description = "角色控制器")
@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService IRoleService;

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
}
