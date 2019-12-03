package com.sbot.modules.system.repository;

import com.sbot.common.base.BaseRepository;
import com.sbot.modules.system.entity.SysRole;
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
public interface SysRoleRepository extends BaseRepository<SysRole> {

}
