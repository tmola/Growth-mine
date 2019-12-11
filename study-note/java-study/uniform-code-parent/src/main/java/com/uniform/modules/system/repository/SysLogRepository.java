package com.uniform.modules.repository;


import com.uniform.common.base.BaseRepository;
import com.uniform.modules.entity.SysLog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统日志表
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Repository
public interface SysLogRepository extends BaseRepository<SysLog> {

    @Transactional
    @Modifying
    @Query(value = "delete from SysLog t where t.id = ?1\n")
    Integer deleteRecordById(String id);
}
