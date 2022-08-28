package com.cgglyle.admin.service;

import com.cgglyle.admin.query.InitAdminSaveQuery;

/**
 * @author lyle
 * @since 2022/08/28
 */
public interface IInitializationService {

    /**
     * 初始化系统管理员帐号
     *
     * @param query 管理员帐号添加请求
     * @return 是否成功
     */
    boolean initSystem(InitAdminSaveQuery query);
}
