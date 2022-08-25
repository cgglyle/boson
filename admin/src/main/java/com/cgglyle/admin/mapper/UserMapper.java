package com.cgglyle.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cgglyle.admin.model.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lylecgg
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
