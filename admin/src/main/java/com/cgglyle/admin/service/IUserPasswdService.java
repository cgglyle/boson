package com.cgglyle.admin.service;

import com.cgglyle.admin.model.entity.UserPasswdEntity;
import com.cgglyle.admin.query.UserPasswdUpdateQuery;
import com.cgglyle.common.service.BaseService;

import java.io.Serializable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lyle
 * @since 2022-08-17
 */
public interface IUserPasswdService extends BaseService<UserPasswdEntity> {

    /**
     * 根据用户id获取密码
     *
     * @param id 用户id
     * @return 实体
     */
    UserPasswdEntity getByUserId(Serializable id);

    /**
     * 添加密码
     *
     * @param entity 实体对象
     * @return 成功失败
     */
    @Override
    boolean save(UserPasswdEntity entity);

    /**
     * 更新密码
     *
     * @param query 实体
     * @return 成功失败
     */
    boolean update(UserPasswdUpdateQuery query);
}
