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

import com.cgglyle.admin.model.entity.ConfigEntity;
import com.cgglyle.common.service.BaseService;

import java.util.Map;

/**
 * <p>
 * 安全配置 服务类
 * </p>
 *
 * @author lyle
 * @since 2022-08-25
 */
public interface IConfigService extends BaseService<ConfigEntity> {

    /**
     * 从数据库中提取配置信息
     *
     * @return Map<key, value>
     */
    Map<String, String> putConfigToMap();

    /**
     * 根据key提取配置
     *
     * @param key key
     * @return value
     */
    String getValueByKey(String key);

    /**
     * 根据key设置value
     *
     * @param key key
     * @param value value
     * @return ture，false
     */
    boolean setValueByKey(String key ,String value);

}
