package com.cgglyle.common.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cgglyle.common.model.BaseEntity;
import com.cgglyle.common.service.BaseService;

import java.io.Serializable;
import java.time.LocalDate;

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
        entity.setCreateTime(LocalDate.now());
        entity.setUpdateTime(LocalDate.now());
        entity.setIsDeleted(false);
        entity.setIsStatus(true);
        return super.save(entity);
    }

    /**
     * 根据实体更新一条数据
     *
     * @param entity 实体
     * @return <code>true</code>成功<br><code>false</code>失败
     */
    @Override
    public boolean update(T entity) {
        return false;
    }

    /**
     * 根据ID删除一条数据
     *
     * @param id 主键id
     * @return <code>true</code>成功<br><code>false</code>失败
     */
    @Override
    public boolean removeById(Serializable id){
        return false;
    }
}
