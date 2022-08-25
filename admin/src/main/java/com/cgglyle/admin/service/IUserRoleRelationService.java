package com.cgglyle.admin.service;

import com.cgglyle.admin.model.entity.UserRoleRelationEntity;
import com.cgglyle.common.service.BaseService;

import java.io.Serializable;

/**
 * @author lyle
 * @date 2022/08/13
 */
public interface IUserRoleRelationService extends BaseService<UserRoleRelationEntity> {
    /**
     * 获取角色，根据用户
     * @param id 用户id
     * @return 实体
     */
    UserRoleRelationEntity getByUserId(Serializable id);
}
