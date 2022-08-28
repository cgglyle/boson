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
