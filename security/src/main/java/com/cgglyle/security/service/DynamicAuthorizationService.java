package com.cgglyle.security.service;

import java.util.List;
import java.util.Map;

/**
 * 动态权限服务
 *
 * @author lyle
 * @since 2022/08/24
 */
public interface DynamicAuthorizationService {
    /**
     * 获取动态权限关系Map
     * <p>
     * Map<权限url, 角色列表> 权限url需要<b>唯一</b>。
     * @return 权限URL与角色的对应Map
     */
    Map<String, List<Long>> permissionList();

    /**
     * 是否启用匿名用户
     *
     * @return true-启用，false-禁用
     */
    Boolean isAnonymousUser();

}
