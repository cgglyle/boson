package com.cgglyle.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cgglyle.security.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lylecgg
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
