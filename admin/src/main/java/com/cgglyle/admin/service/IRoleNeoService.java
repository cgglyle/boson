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

import com.cgglyle.admin.model.entity.RoleNeoEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 角色Neo4j图关系服务
 *
 * @author lyle
 * @since 2022/08/26
 */
public interface IRoleNeoService {

    /**
     * 返回所有角色关系
     * <p>
     * 会嵌套所有角色的嵌套关系
     *
     * @return 角色关系列表
     */
    List<RoleNeoEntity> list();

    /**
     * 获取父角色列表
     *
     * @return 父角色映射列表
     */
    Map<Long, List<Long>> roleParentList();

    /**
     * 返回一个角色的所有关系，根据Id
     *
     * @param id 角色id
     * @return 角色
     */
    Optional<RoleNeoEntity> findById(Long id);

    /**
     * 获取一个角色的所有父角色，并且去重
     *
     * @param id 角色id
     * @return 经过去重的父角色列表
     */
    List<RoleNeoEntity> getRoleNeoEntityAllParentByRoleId(Long id);

    /**
     * 存储一个角色和关系（关系可选）
     *
     * @param entity 角色实体
     * @return 角色实体
     */
    RoleNeoEntity save(RoleNeoEntity entity);

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

    /**
     * 删除一个角色根据id
     *
     * @param id 角色id
     */
    void deletedById(Long id);

    /**
     * 删除一个角色关系
     * <p>
     * 目前仅支持superior关系
     * 关系方向是 (to) -> (form)
     *
     * @param to 子id
     * @param form 父id
     */
    void deletedRelationship(Long to, Long form);
}
