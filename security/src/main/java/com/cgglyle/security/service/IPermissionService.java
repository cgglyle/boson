package com.cgglyle.security.service;

import com.cgglyle.common.service.BaseService;
import com.cgglyle.security.model.entity.PermissionEntity;

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
}
