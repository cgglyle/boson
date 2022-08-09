package com.cgglyle.permissions.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cgglyle.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lylecgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("per_user_info")
public class UserEntity extends BaseEntity {
    private String nickname;
    private String email;
    private String phone;
}
