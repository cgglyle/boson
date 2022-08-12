package com.cgglyle.permissions.service.impl;

import com.cgglyle.common.service.impl.BaseServiceImpl;
import com.cgglyle.permissions.mapper.UserMapper;
import com.cgglyle.permissions.model.UserEntity;
import com.cgglyle.permissions.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author lylecgg
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserEntity> implements UserService {
}
