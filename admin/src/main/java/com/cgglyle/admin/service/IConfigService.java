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

}
