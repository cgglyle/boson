package com.cgglyle.security.service;

import com.cgglyle.common.service.BaseService;
import com.cgglyle.security.model.entity.RoleInheritanceEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色继承关系表 服务类
 * </p>
 *
 * @author lyle
 * @since 2022-08-14
 */
public interface IRoleInheritanceService extends BaseService<RoleInheritanceEntity> {

    /**
     * 从数据库中提取角色继承关系，存入Map
     * @return 角色关系Map
     */
    Map<Long, List<Long>> putRoleInheritanceToMap();

}
