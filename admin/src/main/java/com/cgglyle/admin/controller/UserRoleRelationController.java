package com.cgglyle.admin.controller;

import com.cgglyle.admin.model.entity.UserRoleRelationEntity;
import com.cgglyle.admin.query.UserRoleRelationSaveQuery;
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
 * @author lyle
 * @date 2022/08/13
 */
@Tag(name = "UserRoleRelation")
@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserRoleRelationController {

    private final com.cgglyle.admin.service.IUserRoleRelationService IUserRoleRelationService;

    private static final String SECURITY_USER_ROLE_RELATION = "安全-用户角色关系模块";

    @UnityLog(module = SECURITY_USER_ROLE_RELATION, method = LogMethodEnum.SEARCH, explain = "查找用户角色关系")
    @Operation(summary = "查看用户角色关系")
    @GetMapping("/userRoleRelation")
    public List<UserRoleRelationEntity> list() {
        return IUserRoleRelationService.list();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @UnityLog(module = SECURITY_USER_ROLE_RELATION, method = LogMethodEnum.SAVE, explain = "添加用户角色关系")
    @Operation(summary = "添加用户角色关系")
    @PostMapping("/userRoleRelation")
    public boolean save(@RequestBody @Valid UserRoleRelationSaveQuery vo, BindingResult bindingResult) {
        UserRoleRelationEntity userRoleRelationEntity = new UserRoleRelationEntity();
        BeanUtils.copyProperties(vo, userRoleRelationEntity);
        return IUserRoleRelationService.save(userRoleRelationEntity);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @UnityLog(module = SECURITY_USER_ROLE_RELATION, method = LogMethodEnum.DELETED, explain = "删除用户角色关系")
    @Operation(summary = "删除用户角色关系")
    @DeleteMapping("/userRoleRelation")
    public boolean remove(Long id) {
        return IUserRoleRelationService.removeById(id);
    }

    @UnityLog(module = SECURITY_USER_ROLE_RELATION, method = LogMethodEnum.SEARCH, explain = "查询总数")
    @Operation(summary = "查询总数")
    @GetMapping("/count")
    public long count() {
        return IUserRoleRelationService.count();
    }
}
