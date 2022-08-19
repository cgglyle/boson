package com.cgglyle.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cgglyle.common.service.impl.BaseServiceImpl;
import com.cgglyle.security.mapper.UserMapper;
import com.cgglyle.security.model.entity.UserEntity;
import com.cgglyle.security.service.IUserService;
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
