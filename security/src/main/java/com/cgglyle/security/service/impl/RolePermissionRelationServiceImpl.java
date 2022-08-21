package com.cgglyle.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cgglyle.common.service.impl.BaseServiceImpl;
import com.cgglyle.common.unity.exception.SystemException;
import com.cgglyle.common.unity.status.SystemErrorCode;
import com.cgglyle.security.mapper.RolePermissionRelationMapper;
import com.cgglyle.security.model.entity.RolePermissionRelationEntity;
import com.cgglyle.security.service.IRolePermissionRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限关联表 服务实现类
 * </p>
 *
 * @author lyle
 * @since 2022-08-21
 */
@Service
public class RolePermissionRelationServiceImpl extends BaseServiceImpl<RolePermissionRelationMapper, RolePermissionRelationEntity> implements IRolePermissionRelationService {

    @Override
    public Long getRoleIdByPermissionId(Long permissionId) {
        QueryWrapper<RolePermissionRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("permission_id", permissionId);
        RolePermissionRelationEntity one = this.getOne(wrapper);
        if (one == null){
            throw new SystemException(SystemErrorCode.SYSTEM_ERROR);
        }
        return one.getRoleId();
    }
}
