package com.uniform.modules.system.services.impl;


import com.uniform.common.base.BaseServiceOperator;
import com.uniform.common.vo.QueryVO;
import com.uniform.modules.system.entity.SysLog;
import com.uniform.modules.system.repository.SysLogRepository;
import com.uniform.modules.system.services.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 系统日志表Service接口实现
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Service
public class SysLogServiceImpl implements SysLogService, Serializable {

    @Autowired
    private SysLogRepository logRepository;

    @Override
    public Map select(QueryVO<SysLog> queryVO) throws Exception {
        return null;
    }

    @Override
    public Map save(List<SysLog> records) throws Exception {
        return null;
    }

    @Override
    public Map deleteByIds(List<String> ids) throws Exception {
        return new BaseServiceOperator().deleteByIds(logRepository, ids);
    }
}