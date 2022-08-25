package com.cgglyle.admin.service;

import com.cgglyle.admin.model.entity.UserEntity;
import com.cgglyle.common.service.BaseService;

/**
 * @author lylecgg
 */
public interface IUserService extends BaseService<UserEntity> {

    /**
     * 获取用户实体，根据用户名
     * @param username 用户名
     * @return 实体
     */
    UserEntity getByUserName(String username);

}
