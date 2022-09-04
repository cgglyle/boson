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

import com.cgglyle.admin.model.entity.RoleInheritanceEntity;
import com.cgglyle.common.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色继承关系表 服务类
 * </p>
 *
 * @author lyle
 * @since 2022-08-14
 */
public interface IRoleInheritanceService extends BaseService<RoleInheritanceEntity> {

    /**
     * 从数据库中提取角色继承关系，存入Map
     * @return 角色关系Map
     */
    Map<Long, List<Long>> putRoleInheritanceToMap();

}
