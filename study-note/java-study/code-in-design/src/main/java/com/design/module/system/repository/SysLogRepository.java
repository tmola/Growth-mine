package com.design.module.system.repository;


import com.design.module.system.entity.SysLog;
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
public interface SysLogRepository extends JpaRepository<SysLog, String>, JpaSpecificationExecutor {

}
