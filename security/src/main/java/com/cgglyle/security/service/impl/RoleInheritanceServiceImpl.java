package com.cgglyle.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cgglyle.common.service.impl.BaseServiceImpl;
import com.cgglyle.security.mapper.RoleInheritanceMapper;
import com.cgglyle.security.model.entity.RoleInheritanceEntity;
import com.cgglyle.security.service.IRoleInheritanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public Map<Long, List<Long>> pushRoleInheritanceToRedis() {
        QueryWrapper<RoleInheritanceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "role_id", "role_parent_id");
        List<RoleInheritanceEntity> list = super.list(queryWrapper);
        Map<Long, List<Long>> roleParentMap = new HashMap<>();
        for (RoleInheritanceEntity roleInheritanceEntity : list) {
            List<Long> roleList = new ArrayList<>();
            // 判断是否已经是最大角色
            if (roleInheritanceEntity.getRoleParentId().equals(ROLE_END)){
                roleList.add(ROLE_END);
                roleParentMap.put(roleInheritanceEntity.getRoleId(), roleList);
                continue;
            }
            // 递归查询所有父角色
            roleInheritanceList(roleList,list,roleInheritanceEntity.getRoleParentId());
            // 按id存入Map中
            if (roleParentMap.containsKey(roleInheritanceEntity.getRoleId())){
                List<Long> longs = roleParentMap.get(roleInheritanceEntity.getRoleId());
                // 重复过滤
                List<Long> collect = Stream.of(longs, roleList)
                        .flatMap(Collection::stream).distinct()
                        .collect(Collectors.toList());
                roleParentMap.put(roleInheritanceEntity.getRoleId(), collect);
            }
            else {
                roleParentMap.put(roleInheritanceEntity.getRoleId(), roleList);
            }
        }
        return roleParentMap;
    }

    /**
     * 递归查找所有父角色
     * @param roleList 所有父角色列表
     * @param list 待递归列表
     * @param roleParentId 父id
     */
    private void roleInheritanceList(List<Long> roleList, List<RoleInheritanceEntity> list, Long roleParentId){
        if (roleParentId.equals(ROLE_END)){
            return;
        }
        for (RoleInheritanceEntity roleInheritanceEntity : list){
            if (roleInheritanceEntity.getRoleId().equals(roleParentId)){
                roleList.add(roleInheritanceEntity.getRoleId());
                roleInheritanceList(roleList,list,roleInheritanceEntity.getRoleParentId());
            }
        }
    }
}
