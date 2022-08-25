package com.cgglyle.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cgglyle.admin.mapper.UserMapper;
import com.cgglyle.admin.model.entity.UserEntity;
import com.cgglyle.admin.service.IUserService;
import com.cgglyle.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author lylecgg
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserEntity> implements IUserService {

    @Override
    public UserEntity getByUserName(String username) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("nickname", username);
        return super.getOne(queryWrapper);
    }
}
