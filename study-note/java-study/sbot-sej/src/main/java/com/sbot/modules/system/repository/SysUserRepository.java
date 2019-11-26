package com.sbot.modules.system.repository;


import com.sbot.modules.system.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * 用户表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, String>,JpaSpecificationExecutor {

    @Transactional
    @Modifying
    @Query(value = "update SysUser t set t.delFlag = 1 where t.id = ?1")
    SysUser deleteUserById(String id);


    @Query(value = "select t from SysUser t where t.delFlag = 0 and t.name = ?1")
    SysUser selectByName(String name);

}
