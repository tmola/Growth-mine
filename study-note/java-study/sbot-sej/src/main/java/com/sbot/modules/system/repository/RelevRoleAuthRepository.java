package com.sbot.modules.system.repository;

import com.sbot.modules.system.entity.RelevRoleAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 角色-权限关联表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Repository
public interface RelevRoleAuthRepository extends JpaRepository<RelevRoleAuth, String>,JpaSpecificationExecutor {

}
