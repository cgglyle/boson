package com.cgglyle.security.service.impl;

import com.cgglyle.common.service.impl.BaseServiceImpl;
import com.cgglyle.security.mapper.RoleMapper;
import com.cgglyle.security.model.RoleEntity;
import com.cgglyle.security.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * @author lyle
 * @date 2022/08/13
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, RoleEntity> implements IRoleService {
}
