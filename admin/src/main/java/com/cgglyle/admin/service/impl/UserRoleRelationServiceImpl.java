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

package com.cgglyle.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cgglyle.admin.mapper.UserRoleRelationMapper;
import com.cgglyle.admin.model.entity.UserRoleRelationEntity;
import com.cgglyle.admin.service.IUserRoleRelationService;
import com.cgglyle.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author lyle
 * @date 2022/08/13
 */
@Service
public class UserRoleRelationServiceImpl extends BaseServiceImpl<UserRoleRelationMapper, UserRoleRelationEntity> implements IUserRoleRelationService {
    /**
     * 获取角色，根据用户
     *
     * @param id 用户id
     * @return 实体
     */
    @Override
    public UserRoleRelationEntity getByUserId(Serializable id) {
        QueryWrapper<UserRoleRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        return super.getOne(wrapper);
    }
}
