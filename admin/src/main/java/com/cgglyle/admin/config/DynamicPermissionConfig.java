package com.cgglyle.admin.config;

import com.cgglyle.admin.model.entity.PermissionEntity;
import com.cgglyle.admin.model.entity.UserEntity;
import com.cgglyle.admin.model.entity.UserPasswdEntity;
import com.cgglyle.admin.model.entity.UserRoleRelationEntity;
import com.cgglyle.admin.service.*;
import com.cgglyle.common.model.UserInfo;
import com.cgglyle.common.unity.status.ClientErrorCode;
import com.cgglyle.security.service.DynamicAuthorizationService;
import com.cgglyle.security.service.ILoginUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 安全模块配置
 *
 * @author lyle
 * @since 2022/08/25
 */
@Configuration
@RequiredArgsConstructor
public class DynamicPermissionConfig {
    private final IPermissionService permissionService;
    private final IRoleInheritanceService roleInheritanceService;
    private final IRolePermissionRelationService rolePermissionRelationService;
    private final IUserRoleRelationService userRoleRelationService;
    private final IUserPasswdService userPasswdService;
    private final IUserService userService;
    private final IConfigService configService;

    /**
     * 动态权限授权
     * @return 权限与角色的关系
     */
    @Bean
    public DynamicAuthorizationService dynamicPermissionService(){
        return new DynamicAuthorizationService() {
            @Override
            public Map<String, List<Long>> permissionList() {
                Map<Long, List<Long>> roelInheritanceMap = roleInheritanceService.putRoleInheritanceToMap();
                return permissionService.list().stream()
                        .collect(Collectors.toMap(PermissionEntity::getPermissionUrl,
                                permissionEntity -> roelInheritanceMap.get(rolePermissionRelationService
                                        .getRoleIdByPermissionId(permissionEntity.getId()))));
            }

            @Override
            public Boolean isAnonymousUser() {
                return Boolean.valueOf(configService.getValueByKey("isAnonymousUser"));
            }
        };
    }

    /**
     * 用户认证
     *
     * @return 认证信息
     */
    @Bean
    public ILoginUserDetailsService loginUserDetailsService(){
        return username -> {
            UserEntity entity = userService.getByUserName(username);
            if (entity == null || entity.getId() == null){
                throw new UsernameNotFoundException(ClientErrorCode.USERNAME_NOTFOUND.getMsg());
            }
            UserRoleRelationEntity userRoleRelationEntity = userRoleRelationService.getByUserId(entity.getId());
            UserPasswdEntity userPasswdEntity = userPasswdService.getByUserId(entity.getId());
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(entity.getId());
            userInfo.setUsername(entity.getNickname());
            userInfo.setPassword(userPasswdEntity.getUserPasswd());
            userInfo.setRoleId(userRoleRelationEntity.getRoleId());
            userInfo.setAccountNonLocked(!entity.getIsStatus());
            userInfo.setEnabled(!entity.getIsStatus());
            if (entity.getExpiredTime().equals(-1L) ||
                    entity.getExpiredTime() > System.currentTimeMillis()){
                userInfo.setAccountNonExpired(true);
            }
            if (userPasswdEntity.getExpiredTime().equals(-1L) ||
                    userPasswdEntity.getExpiredTime() > System.currentTimeMillis()){
                userInfo.setCredentialsNonExpired(true);
            }
            return userInfo;
        };
    }
}
