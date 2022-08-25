package com.cgglyle.admin.service;

import com.cgglyle.admin.model.entity.RolePermissionRelationEntity;
import com.cgglyle.common.service.BaseService;

/**
 * <p>
 * 角色权限关联表 服务类
 * </p>
 *
 * @author lyle
 * @since 2022-08-21
 */
public interface IRolePermissionRelationService extends BaseService<RolePermissionRelationEntity> {

    /**
     * 获取角色根据权限
     *
     * @param permissionId 权限id
     * @return 角色id
     */
    Long getRoleIdByPermissionId(Long permissionId);

}
