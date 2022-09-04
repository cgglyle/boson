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

package com.cgglyle.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cgglyle.common.model.BaseEntity;
import com.cgglyle.common.model.UserInfo;
import com.cgglyle.common.service.BaseService;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 顶级Service实现类
 *
 * @author lylecgg
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity>
        extends ServiceImpl<M, T> implements BaseService<T> {
    /**
     * 根据实体存储一条数据
     *
     * @param entity 实体
     * @return <code>true</code>成功<br><code>false</code>失败
     */
    @Override
    public boolean save(T entity) {
        entity.setIsStatus(false);
        return super.save(entity);
    }

    @Override
    public boolean update(Wrapper<T> wrapper) {
        if (wrapper instanceof UpdateWrapper<T>){
            ((UpdateWrapper<T>) wrapper).set("update_time", LocalDateTime.now());
            return super.update(wrapper);
        }
        return super.update(wrapper);
    }

    /**
     * 根据实体更新一条数据
     *
     * @param entity 实体
     * @return <code>true</code>成功<br><code>false</code>失败
     */
    @Override
    public boolean updateById(T entity) {
        entity.setUpdateTime(LocalDateTime.now());
        entity.setUpdateUserId(getUserId());
        return super.updateById(entity);
    }

    /**
     * 根据ID删除一条数据
     *
     * @param id 主键id
     * @return <code>true</code>成功<br><code>false</code>失败
     */
    @Override
    public boolean removeById(Serializable id){
        return super.removeById(id);
    }

    /**
     * 获取当前登录用户id
     * @return 当前登录用户id
     */
    private Long getUserId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = (UserInfo) principal;
        return userInfo.getUserId();
    }
}
