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

import com.cgglyle.admin.model.entity.PermissionNeoEntity;

/**
 * @author lyle
 * @since 2022/08/27
 */
public interface IPermissionNeoService {

    PermissionNeoEntity save(PermissionNeoEntity entity);

    /**
     * 存储一个角色的关系，只存储关系
     * <p>
     * 目前仅支持superior关系
     * 关系方向是 (to) -> (form)
     *
     * @param to 子id
     * @param form 父id
     */
    void saveRelationship(Long to, Long form);

    void deletedAllPermission();
}
