package com.sbot.modules.system.repository;


import com.sbot.common.base.BaseRepository;
import com.sbot.modules.system.entity.RelevUserRole;
import com.sbot.modules.system.entity.SysAuthority;
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
public interface SysAuthorityRepository extends BaseRepository<SysAuthority> {

}
