package com.uniform.modules.services.impl;


import com.sbot.common.annotation.DictResult;
import com.sbot.common.base.BaseServiceOperator;
import com.sbot.common.utils.QueryStrategy;
import com.sbot.common.vo.QueryVO;
import com.sbot.modules.system.entity.SysUser;
import com.sbot.modules.system.repository.SysUserRepository;
import com.sbot.modules.system.services.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户表Service接口实现
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Service("SysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserRepository userRepository;

    @Override
    public Map save(List<SysUser> users) throws Exception {
        return BaseServiceOperator.save(userRepository, users);
    }

    @Override
    public Map deleteByIds(List<String> ids) throws Exception {
        return BaseServiceOperator.deleteByIds(userRepository, ids);
    }


    @DictResult
    @Override
    public Map select(QueryVO<SysUser> queryVO) throws Exception {
        QueryStrategy queryStrategy = new QueryStrategy();
        queryStrategy.setIgnoredFields("sexDictText");
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "createTime"));
        return BaseServiceOperator.select(userRepository, queryVO, queryStrategy, orders);
    }
}