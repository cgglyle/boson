package com.cgglyle.admin.service;

import com.cgglyle.admin.model.entity.PermissionNeoEntity;

/**
 * @author lyle
 * @since 2022/08/27
 */
public interface IPermissionNeoService {

    PermissionNeoEntity save(PermissionNeoEntity entity);

    /**
     * 存储一个角色的关系，只存储关系
     * <p>
     * 目前仅支持superior关系
     * 关系方向是 (to) -> (form)
     *
     * @param to 子id
     * @param form 父id
     */
    void saveRelationship(Long to, Long form);

    void deletedAllPermission();
}
