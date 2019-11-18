package com.design.module.system.repository;


import com.design.module.system.entity.RelevUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 用户-角色关联表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Repository
public interface RelevUserRoleRepository extends JpaRepository<RelevUserRole, String>,JpaSpecificationExecutor {

}
