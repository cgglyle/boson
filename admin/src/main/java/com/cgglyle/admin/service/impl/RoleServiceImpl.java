package com.cgglyle.admin.service.impl;

import com.cgglyle.admin.mapper.RoleMapper;
import com.cgglyle.admin.model.entity.RoleEntity;
import com.cgglyle.admin.service.IRoleService;
import com.cgglyle.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author lyle
 * @date 2022/08/13
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, RoleEntity> implements IRoleService {
}
