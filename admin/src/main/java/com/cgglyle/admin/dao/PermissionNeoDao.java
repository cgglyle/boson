package com.cgglyle.admin.dao;

import com.cgglyle.admin.model.entity.PermissionNeoEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author lyle
 * @since 2022/08/27
 */
@Repository
public interface PermissionNeoDao extends Neo4jRepository<PermissionNeoEntity, Long> {

    @Query(value = "match (a:Permission {permissionId: $to}), (b:Role{roleId: $form}) create (b) - [r:have_permission]->(a) ")
    void saveRelationship(Long to, Long form);
}
