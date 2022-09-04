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

package com.cgglyle.admin.dao;

import com.cgglyle.admin.model.entity.RoleNeoEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lyle
 * @since 2022/08/26
 */
@Repository
public interface RoleNeoDao extends Neo4jRepository<RoleNeoEntity, Long> {

    @Query(value = "MATCH (n:Role {roleId : $id}) - [:superior*0..] -> (parent:Role) return distinct parent")
    List<RoleNeoEntity> getRoleNeoEntityAllParentByRoleId(Long id);

    @Query(value = "match (n:Role{roleId:$to})-[s:superior]->(p:Role{roleId:$form}) delete s")
    void deleteRelationship(Long to, Long form);

    @Query(value = "match (a:Role {roleId: $to}), (b:Role{roleId: $form}) create (a) - [r:superior]->(b) ")
    void saveRelationship(Long to, Long form);


}
