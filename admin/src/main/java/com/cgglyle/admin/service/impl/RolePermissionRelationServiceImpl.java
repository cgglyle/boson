/*
 * Copyright 2022 Cgglyle
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cgglyle.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cgglyle.admin.mapper.RolePermissionRelationMapper;
import com.cgglyle.admin.model.entity.RolePermissionRelationEntity;
import com.cgglyle.admin.service.IRolePermissionRelationService;
import com.cgglyle.common.service.impl.BaseServiceImpl;
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
        // 如果没有配置权限，默认提供3L匿名权限
        if (one == null){
            return 3L;
        }
        return one.getRoleId();
    }
}
