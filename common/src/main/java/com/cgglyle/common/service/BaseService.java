package com.cgglyle.common.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;


/**
 * 公共顶级Service服务接口
 *
 * @author lylecgg
 */
public interface BaseService<T> extends IService<T> {

    /**
     * 根据实体存储一条数据
     *
     * @param entity 实体
     * @return <code>true</code>成功<br><code>false</code>失败
     */
    boolean save(T entity);

    /**
     * 根据实体更新一条数据
     *
     * @param entity 实体
     * @return <code>true</code>成功<br><code>false</code>失败
     */
    boolean update(T entity);

    /**
     * 根据ID删除一条数据
     *
     * @param id 主键id
     * @return <code>true</code>成功<br><code>false</code>失败
     */
    boolean removeById(Serializable id);
}
