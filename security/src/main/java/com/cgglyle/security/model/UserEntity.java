package com.cgglyle.security.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cgglyle.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @author lylecgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("per_user_info")
public class UserEntity extends BaseEntity {
    @NotEmpty(message = "名字不能为空")
    private String nickname;
    @NotEmpty(message = "邮箱不能为空")
    private String email;
    @NotEmpty(message = "电话不能为空")
    private String phone;
}
