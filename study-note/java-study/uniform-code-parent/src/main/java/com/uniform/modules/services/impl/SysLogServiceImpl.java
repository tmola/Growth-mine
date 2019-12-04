package com.uniform.modules.services.impl;


import com.sbot.common.vo.QueryVO;
import com.sbot.modules.system.entity.SysLog;
import com.sbot.modules.system.services.SysLogService;
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
        return null;
    }
}