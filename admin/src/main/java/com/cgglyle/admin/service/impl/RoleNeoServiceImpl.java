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

import com.cgglyle.admin.dao.RoleNeoDao;
import com.cgglyle.admin.model.entity.RoleNeoEntity;
import com.cgglyle.admin.service.IRoleNeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 角色Neo4j图关系服务实现
 *
 * @author lyle
 * @since 2022/08/26
 */
@Service
@RequiredArgsConstructor
public class RoleNeoServiceImpl implements IRoleNeoService {
    private final RoleNeoDao roleNeoDao;

    @Override
    public List<RoleNeoEntity> list() {
        return roleNeoDao.findAll();
    }

    @Override
    public Map<Long, List<Long>> roleParentList() {
        List<RoleNeoEntity> list = list();
        Map<Long, List<Long>> roleParentList = new HashMap<>();
        list.forEach(roleNeoEntity -> {
            List<RoleNeoEntity> roleParentEntityList = getRoleNeoEntityAllParentByRoleId(roleNeoEntity.getRoleId());
            List<Long> collect = roleParentEntityList.stream().map(RoleNeoEntity::getRoleId).toList();
            roleParentList.put(roleNeoEntity.getRoleId(), collect);
        });
        return roleParentList;
    }

    @Override
    public Optional<RoleNeoEntity> findById(Long id) {
        return roleNeoDao.findById(id);
    }

    @Override
    public List<RoleNeoEntity> getRoleNeoEntityAllParentByRoleId(Long id) {
        return roleNeoDao.getRoleNeoEntityAllParentByRoleId(id);
    }

    @Override
    public RoleNeoEntity save(RoleNeoEntity entity) {
        return roleNeoDao.save(entity);
    }

    @Override
    public void saveRelationship(Long to, Long form) {
        roleNeoDao.saveRelationship(to, form);
    }

    @Override
    public void deletedById(Long id) {
        roleNeoDao.deleteById(id);
    }

    @Override
    public void deletedRelationship(Long to, Long form) {
        roleNeoDao.deleteRelationship(to, form);
    }
}
