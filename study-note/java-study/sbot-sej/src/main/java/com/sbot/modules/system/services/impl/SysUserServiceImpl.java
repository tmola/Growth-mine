package com.sbot.modules.system.services.impl;


import com.alibaba.fastjson.JSONArray;

import com.sbot.common.annotation.DictResult;
import com.sbot.common.enums.ResultCode;
import com.sbot.common.exception.ProjectException;
import com.sbot.common.utils.OptRetMapUtil;
import com.sbot.common.utils.QueryStrategy;
import com.sbot.common.utils.ToolUtil;
import com.sbot.common.vo.QueryVO;
import com.sbot.modules.system.entity.SysUser;
import com.sbot.modules.system.repository.SysDictRepository;
import com.sbot.modules.system.repository.SysUserRepository;
import com.sbot.modules.system.services.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * 用户表Service接口实现
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Service("SysUserService")
public class SysUserServiceImpl implements SysUserService, Serializable {
    @Autowired
    private SysUserRepository userRepository;

    @Autowired
    private SysDictRepository dictRepository;

    @Override
    public Map save(List<SysUser> users) {
        if (ToolUtil.isEmpty(users)) return OptRetMapUtil.optError("保存的数据不能为空");
        Integer total = users.size();
        Integer success = 0;
        Integer field = 0;
        List<SysUser> fieldList = new ArrayList<>();
        for (SysUser user : users) {
            if (ToolUtil.isEmpty(user.getId())) {
                user.setId(ToolUtil.randomID35());
                user.setDelFlag(0);
                user.setCreateTime(new Date());
            } else {
                user.setModifyTime(new Date());
            }
            if (ToolUtil.isEmpty(user.getPassword())) {
                user.setPassword("123456");
            }
            if (ToolUtil.isNotEmpty(user.getSexDictText())) {
                String sex = dictRepository.getCode("sex", user.getSexDictText());
                if (null != sex)
                    user.setSex(sex);
            }
            SysUser savedUser = null;
            try {
                savedUser = userRepository.saveAndFlush(user);
            } catch (Exception e) {
                e.printStackTrace();
                field++;
                fieldList.add(user);
                continue;
            }
            if (Objects.isNull(savedUser)) {
                field++;
                fieldList.add(user);
            } else success++;
        }
        return OptRetMapUtil.saveOptResult(total, success, field, fieldList);
    }

    @Override
    public Map delete(List<String> ids) {
        if (ToolUtil.isEmpty(ids)) return OptRetMapUtil.optError("删除的数据不能为空");
        Integer total = ids.size();
        Integer success = 0;
        Integer field = 0;
        List<String> fieldList = new ArrayList<>();
        for (String id : ids) {
            SysUser delUser = userRepository.deleteUserById(id);
            if (Objects.isNull(delUser)) {
                field++;
                fieldList.add(id);
            } else success++;
        }

        return OptRetMapUtil.deleteOptResult(total, success, field, fieldList);
    }

    @DictResult
    @Override
    public Map select(QueryVO<SysUser> conditions) throws ProjectException {
        QueryStrategy queryStrategy = new QueryStrategy();
        queryStrategy.setIgnoredFields("sexDictText");
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "createTime"));
        if (conditions.isPageable()) {
            if (conditions.getPage() < 0 || conditions.getPageSize() <= 0)
                throw new ProjectException(ResultCode.exceptionError);
            Page page = userRepository.findAll(QueryStrategy.ofAllLikeMatch(conditions, queryStrategy), conditions.ofPage(orders));
            return OptRetMapUtil.selectOptResult(page);
        } else {
            List list = null;
            try {
                list = userRepository.findAll(QueryStrategy.ofAllLikeMatch(conditions, queryStrategy), Sort.by(orders));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return OptRetMapUtil.selectOptResult(list);
        }
    }

    @Override
    public Map uploadExcelData(List<Object> datas) throws Exception {
        JSONArray jsonArray = new JSONArray(datas);
        List<SysUser> users = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++)
            users.add(jsonArray.getJSONObject(i).toJavaObject(SysUser.class));
        return save(users);
    }
}