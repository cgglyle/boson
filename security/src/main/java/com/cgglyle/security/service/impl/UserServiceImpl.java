package com.cgglyle.security.service.impl;

import com.cgglyle.common.service.impl.BaseServiceImpl;
import com.cgglyle.security.mapper.UserMapper;
import com.cgglyle.security.model.UserEntity;
import com.cgglyle.security.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author lylecgg
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserEntity> implements IUserService {
}
