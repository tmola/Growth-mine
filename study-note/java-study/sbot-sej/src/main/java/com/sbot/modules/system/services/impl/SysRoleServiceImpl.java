package com.sbot.modules.system.services.impl;


import com.sbot.common.vo.QueryVO;
import com.sbot.modules.system.entity.SysRole;
import com.sbot.modules.system.repository.SysRoleRepository;
import com.sbot.modules.system.services.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 角色表Service接口实现
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Service
public class SysRoleServiceImpl implements SysRoleService, Serializable {

    @Autowired
    private SysRoleRepository roleRepository;

    @Override
    public Map select(QueryVO<SysRole> queryVO) throws Exception {
        return null;
    }

    @Override
    public Map save(List<SysRole> records) throws Exception {
        return null;
    }

    @Override
    public Map deleteByIds(List<String> ids) throws Exception {
        return null;
    }
}