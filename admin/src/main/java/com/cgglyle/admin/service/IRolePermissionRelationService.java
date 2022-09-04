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

package com.cgglyle.admin.service;

import com.cgglyle.admin.model.entity.RolePermissionRelationEntity;
import com.cgglyle.common.service.BaseService;

/**
 * <p>
 * 角色权限关联表 服务类
 * </p>
 *
 * @author lyle
 * @since 2022-08-21
 */
public interface IRolePermissionRelationService extends BaseService<RolePermissionRelationEntity> {

    /**
     * 获取角色根据权限
     *
     * @param permissionId 权限id
     * @return 角色id
     */
    Long getRoleIdByPermissionId(Long permissionId);

}
