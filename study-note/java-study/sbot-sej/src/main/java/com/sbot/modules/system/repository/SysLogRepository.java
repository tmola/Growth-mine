package com.sbot.modules.system.repository;


import com.sbot.common.base.BaseRepository;
import com.sbot.modules.system.entity.RelevUserRole;
import com.sbot.modules.system.entity.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 系统日志表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Repository
public interface SysLogRepository extends BaseRepository<SysLog> {

}
