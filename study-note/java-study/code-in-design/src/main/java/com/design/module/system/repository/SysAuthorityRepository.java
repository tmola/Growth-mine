package com.design.module.system.repository;


import com.design.module.system.entity.SysAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 权限表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Repository
public interface SysAuthorityRepository extends JpaRepository<SysAuthority, String>, JpaSpecificationExecutor {

}
