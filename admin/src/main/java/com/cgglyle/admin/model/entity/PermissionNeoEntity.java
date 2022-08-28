package com.cgglyle.admin.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

/**
 * 权限NEO实体
 *
 * @author lyle
 * @since 2022/08/27
 */
@Node("Permission")
@Data
@Accessors(chain = true)
public class PermissionNeoEntity {

    @Id
    private Long permissionId;
    private String permissionUrl;
    @Relationship(type = "have_permission", direction = Relationship.Direction.INCOMING)
    private List<RoleNeoEntity> role;
}
