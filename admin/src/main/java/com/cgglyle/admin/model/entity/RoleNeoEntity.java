/*
 * Copyright 2022 Cgglyle
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
