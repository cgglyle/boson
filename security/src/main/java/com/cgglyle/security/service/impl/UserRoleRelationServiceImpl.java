package com.cgglyle.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cgglyle.common.service.impl.BaseServiceImpl;
import com.cgglyle.security.mapper.UserRoleRelationMapper;
import com.cgglyle.security.model.entity.UserRoleRelationEntity;
import com.cgglyle.security.service.IUserRoleRelationService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author lyle
 * @date 2022/08/13
 */
@Service
public class UserRoleRelationServiceImpl extends BaseServiceImpl<UserRoleRelationMapper, UserRoleRelationEntity> implements IUserRoleRelationService {
    /**
     * 获取角色，根据用户
     *
     * @param id 用户id
     * @return 实体
     */
    @Override
    public UserRoleRelationEntity getByUserId(Serializable id) {
        QueryWrapper<UserRoleRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        return super.getOne(wrapper);
    }
}
