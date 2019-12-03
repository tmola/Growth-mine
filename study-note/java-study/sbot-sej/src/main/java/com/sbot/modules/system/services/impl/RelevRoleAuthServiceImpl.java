package com.sbot.modules.system.services.impl;



import com.sbot.common.vo.QueryVO;
import com.sbot.modules.system.entity.RelevRoleAuth;
import com.sbot.modules.system.services.RelevRoleAuthService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 角色-权限关联表Service接口实现
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Service
public class RelevRoleAuthServiceImpl implements RelevRoleAuthService, Serializable {


    @Override
    public Map select(QueryVO<RelevRoleAuth> queryVO) throws Exception {
        return null;
    }

    @Override
    public Map save(List<RelevRoleAuth> records) throws Exception {
        return null;
    }

    @Override
    public Map deleteByIds(List<String> ids) throws Exception {
        return null;
    }
}