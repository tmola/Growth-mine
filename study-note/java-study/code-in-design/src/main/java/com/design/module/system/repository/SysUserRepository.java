package com.design.module.system.repository;


import com.code.modules.system.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 用户表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, String>,JpaSpecificationExecutor {

    SysUser getByName(String name);
}
