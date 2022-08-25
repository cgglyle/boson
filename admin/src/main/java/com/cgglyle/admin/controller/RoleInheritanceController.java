package com.cgglyle.admin.controller;

import com.cgglyle.admin.model.entity.RoleInheritanceEntity;
import com.cgglyle.admin.query.RoleInheritanceSaveQuery;
import com.cgglyle.admin.service.IRoleInheritanceService;
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
import java.util.Map;

/**
 * <p>
 * 角色继承关系表 前端控制器
 * </p>
 *
 * @author lyle
 * @since 2022-08-14
 */
@Tag(name = "RoleInheritance")
@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleInheritanceController {

    private final IRoleInheritanceService roleInheritanceService;

    private static final String SECURITY_ROLE_INHERITANCE = "安全-角色继承模块";


    @UnityLog(module = SECURITY_ROLE_INHERITANCE, method = LogMethodEnum.SEARCH, explain = "查看全部角色继承关系")
    @Operation(summary = "查看全部角色继承关系")
    @GetMapping("/inheritances")
    public List<RoleInheritanceEntity> list() {
        return roleInheritanceService.list();
    }

    @UnityLog(module = SECURITY_ROLE_INHERITANCE, method = LogMethodEnum.SEARCH, explain = "根据ID查找角色继承关系")
    @Operation(summary = "根据ID查找角色继承关系")
    @GetMapping("/inheritances/{id}")
    public RoleInheritanceVo getById(@PathVariable(value = "id") Long id){
        RoleInheritanceVo roleInheritanceVo = new RoleInheritanceVo();
        BeanUtils.copyProperties(roleInheritanceService.getById(id),roleInheritanceVo);
        return roleInheritanceVo;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @UnityLog(module = SECURITY_ROLE_INHERITANCE, method = LogMethodEnum.SAVE, explain = "添加角色继承关系")
    @Operation(summary = "添加角色继承关系")
    @PostMapping("/inheritances")
    public boolean save(@RequestBody @Valid RoleInheritanceSaveQuery vo, BindingResult bindingResult) {
        RoleInheritanceEntity roleInheritanceEntity = new RoleInheritanceEntity();
        BeanUtils.copyProperties(vo, roleInheritanceEntity);
        return roleInheritanceService.save(roleInheritanceEntity);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @UnityLog(module = SECURITY_ROLE_INHERITANCE, method = LogMethodEnum.DELETED, explain = "删除角色继承关系")
    @Operation(summary = "删除角色继承关系")
    @DeleteMapping("/inheritances")
    public void remove(Long id) {
        roleInheritanceService.removeById(id);
    }

    @UnityLog(module = SECURITY_ROLE_INHERITANCE, method = LogMethodEnum.SEARCH, explain = "查询总数")
    @Operation(summary = "查询总数")
    @GetMapping("/inheritances/counts")
    public long count() {
        return roleInheritanceService.count();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @UnityLog(module = SECURITY_ROLE_INHERITANCE, method = LogMethodEnum.SAVE, explain = "Redis添加角色继承关系")
    @Operation(summary = "将角色父关系存入MAP")
    @PostMapping("/inheritances/push-map")
    public Map<Long, List<Long>> pushRoleInheritanceToMap(){
        return roleInheritanceService.putRoleInheritanceToMap();
    }
}
