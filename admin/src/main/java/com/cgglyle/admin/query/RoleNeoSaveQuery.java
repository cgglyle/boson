package com.cgglyle.admin.query;

import com.cgglyle.admin.model.entity.RoleNeoEntity;
import lombok.Data;

import java.util.List;

/**
 * @author lyle
 * @since 2022/08/26
 */
@Data
public class RoleNeoSaveQuery {
    private Long roleId;
    private String roleName;
    private List<RoleNeoEntity> roleNeoEntityList;
}
