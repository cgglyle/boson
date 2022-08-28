package com.cgglyle.admin.model.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

/**
 * Neo4j 角色实体
 *
 * @author lyle
 * @since 2022/08/26
 */
@Data
@Node("Role")
public class RoleNeoEntity {
    @Id
    private Long roleId;

    private String roleName;

    /**
     * 角色关系
     */
    @Relationship(type = "superior", direction = Relationship.Direction.OUTGOING)
    private List<RoleNeoEntity> roleNeoEntityList;
}
