package com.cgglyle.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cgglyle.admin.mapper.UserPasswdMapper;
import com.cgglyle.admin.model.entity.UserPasswdEntity;
import com.cgglyle.admin.query.UserPasswdUpdateQuery;
import com.cgglyle.admin.service.IUserPasswdService;
import com.cgglyle.common.service.impl.BaseServiceImpl;
import com.cgglyle.common.unity.exception.ClientException;
import com.cgglyle.common.unity.status.ClientErrorCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyle
 * @since 2022-08-17
 */
@Service
public class UserPasswdServiceImpl extends BaseServiceImpl<UserPasswdMapper, UserPasswdEntity> implements IUserPasswdService {
    /**
     * 根据用户id获取密码
     *
     * @param id 用户id
     * @return 实体
     */
    @Override
    public UserPasswdEntity getByUserId(Serializable id) {
        QueryWrapper<UserPasswdEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        return super.getOne(wrapper);
    }

    @Override
    public boolean save(UserPasswdEntity entity) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String encode = encoder.encode(entity.getUserPasswd());
        entity.setUserPasswd(encode);
        return super.save(entity);
    }

    /**
     * 更新密码
     *
     * @param query 实体
     * @return 成功失败
     */
    @Override
    public boolean update(UserPasswdUpdateQuery query) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        UserPasswdEntity entity = this.getByUserId(query.getUserId());
        if (entity.getIsStatus()){
            throw new ClientException(ClientErrorCode.DISABLED);
        }
        boolean matches = encoder.matches(query.getOldPasswd(), entity.getUserPasswd());
        if (!matches){
            throw new ClientException(ClientErrorCode.PASSWORD_ERROR);
        }
        entity.setUserPasswd(encoder.encode(query.getNewPasswd()));
        return super.updateById(entity);
    }

}
