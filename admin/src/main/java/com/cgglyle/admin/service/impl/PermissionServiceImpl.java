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
import com.cgglyle.admin.mapper.PermissionMapper;
import com.cgglyle.admin.model.entity.PermissionEntity;
import com.cgglyle.admin.service.IPermissionService;
import com.cgglyle.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 接口权限表 服务实现类
 * </p>
 *
 * @author lyle
 * @since 2022-08-21
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionMapper, PermissionEntity> implements IPermissionService {

    /**
     * 将权限表提取存入Map中
     *
     * @return 权限map
     */
    @Override
    public Map<String, Long> putPermissionToMap() {
        List<PermissionEntity> list = this.list();
        return list.stream().collect(Collectors.toMap(PermissionEntity::getPermissionUrl, PermissionEntity::getId));
    }

    /**
     * 判断数据库中url是否存在
     *
     * @param url 权限url
     * @return 存在为true 否则false
     */
    @Override
    public boolean isPermissionExist(String url) {
        QueryWrapper<PermissionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("permission_url", url);
        PermissionEntity entity = getOne(wrapper);
        return entity != null && entity.getId() != null;
    }
}
