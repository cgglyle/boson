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

import com.cgglyle.admin.model.entity.PermissionEntity;
import com.cgglyle.common.service.BaseService;

import java.util.Map;

/**
 * <p>
 * 接口权限表 服务类
 * </p>
 *
 * @author lyle
 * @since 2022-08-21
 */
public interface IPermissionService extends BaseService<PermissionEntity> {
    /**
     * 将权限表提取存入Map中
     *
     * @return 权限map
     */
    Map<String, Long> putPermissionToMap();

    /**
     * 判断数据库中url是否存在
     *
     * @param url 权限url
     * @return true, false
     */
    boolean isPermissionExist(String url);
}
