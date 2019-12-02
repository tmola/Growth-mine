package com.sbot.modules.system.services.impl;


import com.alibaba.fastjson.JSONArray;

import com.sbot.common.annotation.DictResult;
import com.sbot.common.base.BaseServiceOperator;
import com.sbot.common.utils.QueryStrategy;
import com.sbot.common.vo.QueryVO;
import com.sbot.modules.system.entity.SysUser;
import com.sbot.modules.system.repository.SysDictRepository;
import com.sbot.modules.system.repository.SysUserRepository;
import com.sbot.modules.system.services.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.*;

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

    @Autowired
    private SysDictRepository dictRepository;


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

    @Override
    public Map uploadExcelData(List<Object> datas) throws Exception {
        JSONArray jsonArray = new JSONArray(datas);
        List<SysUser> users = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            SysUser user =  jsonArray.getJSONObject(i).toJavaObject(SysUser.class);
            user.setSex(dictRepository.getCode("sex", user.getSex()));
            user.setPassword("123456");
            users.add(user);
        }
        return save(users);
    }
}