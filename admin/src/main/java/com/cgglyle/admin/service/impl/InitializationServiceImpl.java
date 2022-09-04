package com.cgglyle.admin.service.impl;

import com.cgglyle.admin.enums.ConfigEnum;
import com.cgglyle.admin.model.entity.*;
import com.cgglyle.admin.query.InitAdminSaveQuery;
import com.cgglyle.admin.service.*;
import com.cgglyle.common.unity.exception.SystemException;
import com.cgglyle.common.unity.status.SystemErrorCode;
import com.cgglyle.security.event.DynamicAuthorizationChangeEvent;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 系统初始化服务
 *
 * @author lyle
 * @since 2022/08/28
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class InitializationServiceImpl implements IInitializationService {

    private final IConfigService configService;
    private final IUserService userService;
    private final IUserPasswdService userPasswdService;
    private final WebApplicationContext webApplicationContext;
    private final IPermissionService permissionService;
    private final IPermissionNeoService permissionNeoService;
    private final IRoleService roleService;
    private final IUserRoleRelationService userRoleRelationService;
    private final IRoleInheritanceService roleInheritanceService;

    @Transactional
    @Override
    public boolean initSystem(InitAdminSaveQuery query) {
//        String isInitializationAdmin = configService.getValueByKey(ConfigEnum.IS_INITIALIZATION_ADMIN);
        long count = userService.count();
//        boolean aBoolean = Boolean.parseBoolean(isInitializationAdmin);
        if (count != 0){
            throw new SystemException(SystemErrorCode.REPEAT_INITIALIZATION_ERROR);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setNickname(query.nickname());
        userEntity.setEmail(query.email());
        userEntity.setCreateUserId(1L);
        userEntity.setUpdateUserId(1L);
        boolean userSave = userService.save(userEntity);
        UserPasswdEntity userPasswdEntity = new UserPasswdEntity();
        userPasswdEntity.setUserId(1L);
        userPasswdEntity.setCreateUserId(1L);
        userPasswdEntity.setUpdateUserId(1L);
        userPasswdEntity.setUserPasswd(query.password());
        boolean userPasswdSave = userPasswdService.save(userPasswdEntity);
        UserRoleRelationEntity userRoleRelationEntity = new UserRoleRelationEntity();
        userRoleRelationEntity.setUserId(1L);
        userRoleRelationEntity.setRoleId(1L);
        userRoleRelationEntity.setCreateUserId(1L);
        userRoleRelationEntity.setUpdateUserId(1L);
        boolean userRoleRelationSave = userRoleRelationService.save(userRoleRelationEntity);
        return userSave && userPasswdSave && userRoleRelationSave;
    }

    /**
     * 初始化配置系统
     * <p>
     * 监听{@link ContextRefreshedEvent}事件
     * Order顺序越小越先执行
     */
    @Order(-2)
    @EventListener(ContextRefreshedEvent.class)
    public void initConfig(){
        long count = configService.count();
        if (count != 0){
            return;
        }
        ConfigEntity configEntity = new ConfigEntity();
        configEntity.setConfigKey(ConfigEnum.IS_ANONYMOUS_USER.getMsg());
        configEntity.setConfigValue("true");
        configEntity.setCreateUserId(1L);
        configEntity.setUpdateUserId(1L);
        configService.save(configEntity);
        log.info("配置系统初始化完成！");
    }

    /**
     * 初始化权限系统
     * <p>
     * 监听{@link ContextRefreshedEvent}事件
     * Order顺序越小越先执行
     */
    @Order(-1)
    @EventListener(ContextRefreshedEvent.class)
    public void initPermission() {
        long count = permissionService.count();
        if (count != 0){
            return;
        }
        permissionUpdate();
        log.info("权限系统初始化完成！");
    }

    /**
     * 初始化角色系统
     * <p>
     * 监听{@link ContextRefreshedEvent}事件
     * Order顺序越小越先执行
     */
    @Order(-3)
    @EventListener(ContextRefreshedEvent.class)
    public void initRole(){
        long count = roleService.count();
        if (count != 0){
            return;
        }
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRoleCode("administrator");
        roleEntity.setRoleName("超级管理员");
        roleEntity.setRoleDescription("最大级别管理员，拥有所有权限");
        roleEntity.setCreateUserId(1L);
        roleEntity.setUpdateUserId(1L);
        roleService.save(roleEntity);
        roleEntity.setId(2L);
        roleEntity.setRoleCode("user");
        roleEntity.setRoleName("用户");
        roleEntity.setRoleDescription("用户");
        roleService.save(roleEntity);
        roleEntity.setId(3L);
        roleEntity.setRoleCode("anonymous");
        roleEntity.setRoleName("匿名");
        roleEntity.setRoleDescription("匿名用户");
        roleService.save(roleEntity);
        log.info("角色系统初始化完成！");
    }

    /**
     * 初始化角色继承系统
     * <p>
     * 监听{@link ContextRefreshedEvent}事件
     * Order顺序越小越先执行
     */
    @Order(-4)
    @EventListener(ContextRefreshedEvent.class)
    public void initRoleInheritance(){
        long count = roleInheritanceService.count();
        if (count != 0){
            return;
        }
        RoleInheritanceEntity roleInheritance = new RoleInheritanceEntity();
        roleInheritance.setId(1L);
        roleInheritance.setRoleId(1L);
        roleInheritance.setRoleParentId(0L);
        roleInheritance.setCreateUserId(1L);
        roleInheritance.setUpdateUserId(1L);
        roleInheritanceService.save(roleInheritance);
        roleInheritance.setId(2L);
        roleInheritance.setRoleId(2L);
        roleInheritance.setRoleParentId(1L);
        roleInheritanceService.save(roleInheritance);
        roleInheritance.setId(3L);
        roleInheritance.setRoleId(3L);
        roleInheritance.setRoleParentId(2L);
        roleInheritanceService.save(roleInheritance);
        log.info("角色继承系统初始化完成！");
    }

    @EventListener(DynamicAuthorizationChangeEvent.class)
    public void permissionUpdate(){
        RequestMappingHandlerMapping mapping = webApplicationContext.getBean(RequestMappingHandlerMapping.class);
        // 拿到Handler适配器中的全部方法
        Map<RequestMappingInfo, HandlerMethod> methodMap = mapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : methodMap.entrySet()) {
            RequestMappingInfo mappingInfo = entry.getKey();
            assert mappingInfo.getPatternsCondition() != null;
            StringBuilder builder = new StringBuilder();
            builder.append(mappingInfo.getMethodsCondition());
            builder.deleteCharAt(0);
            builder.deleteCharAt(builder.length() - 1);
            builder.append(((PathPattern) mappingInfo.getPathPatternsCondition().getPatterns().toArray()[0]).getPatternString());
            List<PermissionEntity> permissionEntities = new ArrayList<>();
            if (!permissionService.isPermissionExist(builder.toString())) {
                PermissionEntity entity = new PermissionEntity();
                entity.setPermissionUrl(builder.toString());
                entity.setCreateUserId(1L);
                entity.setUpdateUserId(1L);
                HandlerMethod value = entry.getValue();
                Method method = value.getMethod();
                Operation annotation = method.getAnnotation(Operation.class);
                if (annotation == null) {
                    entity.setPermissionDescription("");
                    permissionEntities.add(entity);
                } else {
                    String summary = annotation.summary();
                    entity.setPermissionDescription(summary);
                    permissionEntities.add(entity);
                }
            }
            permissionService.saveBatch(permissionEntities);
        }
    }
}
