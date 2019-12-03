package com.sbot.modules.system.services.impl;


import com.sbot.common.vo.QueryVO;
import com.sbot.modules.system.entity.SysAuthority;
import com.sbot.modules.system.services.SysAuthorityService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 权限表Service接口实现
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Service
public class SysAuthorityServiceImpl implements SysAuthorityService, Serializable {


    @Override
    public Map select(QueryVO<SysAuthority> queryVO) throws Exception {
        return null;
    }

    @Override
    public Map save(List<SysAuthority> records) throws Exception {
        return null;
    }

    @Override
    public Map deleteByIds(List<String> ids) throws Exception {
        return null;
    }
}