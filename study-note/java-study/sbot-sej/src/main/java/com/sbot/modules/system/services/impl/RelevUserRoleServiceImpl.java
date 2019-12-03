package com.sbot.modules.system.services.impl;


import com.sbot.common.vo.QueryVO;
import com.sbot.modules.system.entity.RelevUserRole;
import com.sbot.modules.system.services.RelevUserRoleService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 用户-角色关联表Service接口实现
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Service
public class RelevUserRoleServiceImpl implements RelevUserRoleService, Serializable {


    @Override
    public Map select(QueryVO<RelevUserRole> queryVO) throws Exception {
        return null;
    }

    @Override
    public Map save(List<RelevUserRole> records) throws Exception {
        return null;
    }

    @Override
    public Map deleteByIds(List<String> ids) throws Exception {
        return null;
    }
}