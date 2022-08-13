package com.cgglyle.security.controller;

import com.cgglyle.logger.annotaion.UnityLog;
import com.cgglyle.logger.enums.LogMethodEnum;
import com.cgglyle.logger.enums.LogModuleEnum;
import com.cgglyle.security.model.UserRoleRelationEntity;
import com.cgglyle.security.query.UserRoleRelationSaveQuery;
import com.cgglyle.security.service.UserRoleRelationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/userRoleRelation")
public class UserRoleRelationController {

    @Autowired
    private UserRoleRelationService userRoleRelationService;

    @UnityLog(module = LogModuleEnum.SECURITY_USER_ROLE_RELATION, method = LogMethodEnum.SEARCH, explain = "查找用户角色关系")
    @Operation(summary = "查看用户角色关系")
    @GetMapping("/userRoleRelation")
    public List<UserRoleRelationEntity> list() {
        return userRoleRelationService.list();
    }

    @UnityLog(module = LogModuleEnum.SECURITY_USER_ROLE_RELATION, method = LogMethodEnum.SAVE, explain = "添加用户角色关系")
    @Operation(summary = "添加用户角色关系")
    @PostMapping("/userRoleRelation")
    public boolean save(@RequestBody @Valid UserRoleRelationSaveQuery vo, BindingResult bindingResult) {
        UserRoleRelationEntity userRoleRelationEntity = new UserRoleRelationEntity();
        BeanUtils.copyProperties(vo, userRoleRelationEntity);
        return userRoleRelationService.save(userRoleRelationEntity);
    }

    @UnityLog(module = LogModuleEnum.SECURITY_USER_ROLE_RELATION, method = LogMethodEnum.DELETED, explain = "删除用户角色关系")
    @Operation(summary = "删除用户角色关系")
    @DeleteMapping("/userRoleRelation")
    public boolean remove(Long id) {
        return userRoleRelationService.removeById(id);
    }

    @UnityLog(module = LogModuleEnum.SECURITY_USER_ROLE_RELATION, method = LogMethodEnum.SEARCH, explain = "查询总数")
    @Operation(summary = "查询总数")
    @GetMapping("/count")
    public long count() {
        return userRoleRelationService.count();
    }
}
