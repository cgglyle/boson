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

import com.cgglyle.admin.dao.PermissionNeoDao;
import com.cgglyle.admin.model.entity.PermissionNeoEntity;
import com.cgglyle.admin.service.IPermissionNeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyle
 * @since 2022/08/27
 */
@Service
@RequiredArgsConstructor
public class PermissionNeoServiceImpl implements IPermissionNeoService {

    private final PermissionNeoDao permissionNeoDao;

    @Override
    public PermissionNeoEntity save(PermissionNeoEntity entity) {
        List<PermissionNeoEntity> entities = new ArrayList<>();
        entities.add(new PermissionNeoEntity().setPermissionId(1L).setPermissionUrl("GET/users"));
        entities.add(new PermissionNeoEntity().setPermissionId(2L).setPermissionUrl("GET/users/{id}"));
        entities.add(new PermissionNeoEntity().setPermissionId(3L).setPermissionUrl("POST/users"));
        entities.add(new PermissionNeoEntity().setPermissionId(4L).setPermissionUrl("DELETE/users"));
        entities.add(new PermissionNeoEntity().setPermissionId(5L).setPermissionUrl("GET/users/counts"));
        entities.add(new PermissionNeoEntity().setPermissionId(6L).setPermissionUrl("POST/passwd"));
        entities.add(new PermissionNeoEntity().setPermissionId(7L).setPermissionUrl("null/cp/error"));
        entities.add(new PermissionNeoEntity().setPermissionId(8L).setPermissionUrl("GET/admin/permission"));
        entities.add(new PermissionNeoEntity().setPermissionId(9L).setPermissionUrl("GET/permission/{id}"));
        permissionNeoDao.saveAll(entities);
        return null;
    }

    /**
     * 存储一个角色的关系，只存储关系
     * <p>
     * 目前仅支持superior关系
     * 关系方向是 (to) -> (form)
     *
     * @param to   子id
     * @param form 父id
     */
    @Override
    public void saveRelationship(Long to, Long form) {
        permissionNeoDao.saveRelationship(to, form);
    }

    @Override
    public void deletedAllPermission() {
        List<PermissionNeoEntity> all = permissionNeoDao.findAll();
        all.forEach(entity -> {
            permissionNeoDao.deleteById(entity.getPermissionId());
        });
    }
}
