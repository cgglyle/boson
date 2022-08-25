package com.cgglyle.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cgglyle.common.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 安全配置
 * </p>
 *
 * @author lyle
 * @since 2022-08-25
 */
@Getter
@Setter
@TableName("security_config")
@Schema(name = "ConfigEntity", description = "$!{table.comment}")
public class ConfigEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("config_key")
    private String configKey;

    @TableField("config_value")
    private String configValue;
}
