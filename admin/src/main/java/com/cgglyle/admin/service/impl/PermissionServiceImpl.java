package com.cgglyle.admin.service.impl;

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
}
