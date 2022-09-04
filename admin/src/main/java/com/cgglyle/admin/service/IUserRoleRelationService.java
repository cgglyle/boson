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

import com.cgglyle.admin.model.entity.UserRoleRelationEntity;
import com.cgglyle.common.service.BaseService;

import java.io.Serializable;

/**
 * @author lyle
 * @date 2022/08/13
 */
public interface IUserRoleRelationService extends BaseService<UserRoleRelationEntity> {
    /**
     * 获取角色，根据用户
     * @param id 用户id
     * @return 实体
     */
    UserRoleRelationEntity getByUserId(Serializable id);
}
