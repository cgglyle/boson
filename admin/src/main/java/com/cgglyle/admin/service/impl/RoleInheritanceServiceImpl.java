package com.cgglyle.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cgglyle.admin.mapper.RoleInheritanceMapper;
import com.cgglyle.admin.model.entity.RoleInheritanceEntity;
import com.cgglyle.admin.service.IRoleInheritanceService;
import com.cgglyle.common.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色继承关系表 服务实现类
 * </p>
 *
 * @author lyle
 * @since 2022-08-14
 */
@Slf4j
@Service
public class RoleInheritanceServiceImpl extends BaseServiceImpl<RoleInheritanceMapper, RoleInheritanceEntity> implements IRoleInheritanceService {

    private static final Long ROLE_END = 0L;


    /**
     * 从数据库中提取角色继承关系，存入Redis中。
     *
     * @return 成功 失败
     */
    @Override
    public Map<Long, List<Long>> putRoleInheritanceToMap() {
        QueryWrapper<RoleInheritanceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "role_id", "role_parent_id");
        List<RoleInheritanceEntity> list = super.list(queryWrapper);
        // 按照roleID进行分组
        Map<Long, List<Long>> group = list.stream()
                .collect(Collectors.groupingBy(RoleInheritanceEntity::getRoleId, Collectors
                        .mapping(RoleInheritanceEntity::getRoleParentId, Collectors.toList())));
        Map<Long, List<Long>> roleInheritance = new HashMap<>();
        // 递归遍历所有父角色
        group.forEach((roleId, parentRoleIdList)->{
            List<Long> roleList = new ArrayList<>();
            // 添加自身进数组
            roleList.add(roleId);
            getList(roleId, roleList, group, roleInheritance);
            List<Long> collect = roleList.stream().distinct().collect(Collectors.toList());
            roleInheritance.put(roleId, collect);
        });
        return roleInheritance;
    }

    /**
     * 递归查找所有父角色
     * @param group 所有父角色列表
     * @param roleList 返回角色列表
     * @param roleId id
     */
    private void getList(Long roleId, List<Long> roleList, Map<Long, List<Long>> group, Map<Long, List<Long>> roleInheritance){
        if (group.get(roleId).get(0).equals(ROLE_END)){
            return;
        }
        if (roleInheritance.containsKey(roleId)){
            roleList.addAll(roleInheritance.get(roleId));
            return;
        }
        List<Long> integers = group.get(roleId);
        integers.forEach(integer -> {
            roleList.add(integer);
            getList(integer, roleList, group, roleInheritance);
        });
    }
}
