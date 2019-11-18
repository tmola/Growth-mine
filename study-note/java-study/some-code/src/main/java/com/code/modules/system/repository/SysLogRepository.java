package com.code.modules.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.code.modules.system.entity.SysLog;

/**
 * 系统日志表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Repository
public interface SysLogRepository extends JpaRepository<SysLog, String>,JpaSpecificationExecutor {

}
