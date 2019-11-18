package com.design.module.system.repository;

import com.design.module.system.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 角色表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Repository
public interface SysRoleRepository extends JpaRepository<SysRole, String>,JpaSpecificationExecutor {

}
