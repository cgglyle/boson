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
