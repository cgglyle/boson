package com.cgglyle.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cgglyle.admin.mapper.ConfigMapper;
import com.cgglyle.admin.model.entity.ConfigEntity;
import com.cgglyle.admin.service.IConfigService;
import com.cgglyle.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 安全配置 服务实现类
 * </p>
 *
 * @author lyle
 * @since 2022-08-25
 */
@Service
public class ConfigServiceImpl extends BaseServiceImpl<ConfigMapper, ConfigEntity> implements IConfigService {


    /**
     * 从数据库中提取配置信息
     *
     * @return Map<key, value>
     */
    @Override
    public Map<String, String> putConfigToMap() {
        QueryWrapper<ConfigEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("key", "value");
        List<ConfigEntity> list = super.list(queryWrapper);
        return list.stream().collect(Collectors.toMap(ConfigEntity::getConfigKey, ConfigEntity::getConfigValue));
    }

    /**
     * 根据key提取配置
     *
     * @param key key
     * @return value
     */
    @Override
    public String getValueByKey(String key) {
        QueryWrapper<ConfigEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("config_key", key);
        ConfigEntity one = super.getOne(queryWrapper);
        if (one == null){
            return null;
        } else {
            return super.getOne(queryWrapper).getConfigValue();
        }
    }

    /**
     * 根据key设置value
     *
     * @param key   key
     * @param value value
     * @return ture，false
     */
    @Override
    public boolean setValueByKey(String key, String value) {
        UpdateWrapper<ConfigEntity>  wrapper = new UpdateWrapper<>();
        wrapper.eq("config_key", key);
        wrapper.set("config_value", value);
        return super.update(wrapper);
    }


}