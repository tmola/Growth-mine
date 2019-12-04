package com.uniform.modules.services.impl;


import com.uniform.common.annotation.DictResult;
import com.uniform.common.base.BaseServiceOperator;
import com.uniform.common.utils.QueryStrategy;
import com.uniform.common.vo.QueryVO;
import com.uniform.modules.entity.SysUser;
import com.uniform.modules.repository.SysUserRepository;
import com.uniform.modules.services.SysUserService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


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

    @Transactional(propagation = Propagation.NESTED)
    public Map save(List<SysUser> users) throws Exception {
        return new BaseServiceOperator().save(userRepository, users);
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