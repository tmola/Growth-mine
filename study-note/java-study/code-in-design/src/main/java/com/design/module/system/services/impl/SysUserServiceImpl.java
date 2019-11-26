package com.design.module.system.services.impl;


import com.alibaba.fastjson.JSONArray;
import com.design.common.annotation.dictAnnotation.DictResult;
import com.design.common.exception.ProjectException;
import com.design.common.exception.ReturnMessage;
import com.design.common.util.ObjectUtil;
import com.design.common.util.QueryPredicate;
import com.design.common.util.result.OptResult;
import com.design.common.vo.CommonQuery;
import com.design.module.system.entity.SysDict;
import com.design.module.system.entity.SysUser;
import com.design.module.system.repository.SysDictRepository;
import com.design.module.system.repository.SysUserRepository;
import com.design.module.system.services.SysUserService;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * 用户表Service接口实现
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Service("UserService")
public class SysUserServiceImpl implements SysUserService, Serializable {
    @Autowired
    private SysUserRepository userRepository;

    @Autowired
    private SysDictRepository dictRepository;

    @Override
    public Map save(List<SysUser> users) {
        if (ObjectUtil.isEmpty(users)) return OptResult.optError("保存的数据不能为空");
        Integer total = users.size();
        Integer success = 0;
        Integer field = 0;
        List<SysUser> fieldList = new ArrayList<>();
        for (SysUser user : users) {
            if (ObjectUtil.isEmpty(user.getId())) {
                user.setId(ObjectUtil.randomID_35());
                user.setDelFlag(0);
                user.setCreateTime(new Date());
            } else {
                user.setModifyTime(new Date());
            }
            if (ObjectUtil.isEmpty(user.getPassword())) {
                user.setPassword("123456");
            }
            if (ObjectUtil.isNotEmpty(user.getSexDictText())) {
                String sex = dictRepository.getCode("sex", user.getSexDictText());
                if (null != sex)
                    user.setSex(sex);
            }
            SysUser savedUser = null;
            try {
                savedUser = userRepository.saveAndFlush(user);
            }catch (Exception e){
                e.printStackTrace();
                field++;
                fieldList.add(user);
                continue;
            }
            if (ObjectUtil.isNull(savedUser)) {
                field++;
                fieldList.add(user);
            } else success++;
        }
        return OptResult.saveResult(total, success, field, fieldList);
    }

    @Override
    public Map delete(List<String> ids) {
        if (ObjectUtil.isEmpty(ids)) return OptResult.optError("删除的数据不能为空");
        Integer total = ids.size();
        Integer success = 0;
        Integer field = 0;
        List<String> fieldList = new ArrayList<>();
        for (String id : ids) {
            SysUser delUser = userRepository.deleteUserById(id);
            if (ObjectUtil.isNull(delUser)) {
                field++;
                fieldList.add(id);
            } else success++;
        }

        return OptResult.deleteResult(total, success, field, fieldList);
    }

    @DictResult
    @Override
    public Map select(CommonQuery<SysUser> conditions) {
        QueryPredicate queryPredicate = new QueryPredicate();
        if (conditions.isPageable()) {
            Page page = userRepository.findAll(QueryPredicate.ofAllLikeMatch(conditions, queryPredicate), conditions.ofPage());
            return OptResult.selectResult(page);
        } else {
            List list = userRepository.findAll(QueryPredicate.ofAllLikeMatch(conditions, queryPredicate));
            return OptResult.selectResult(list);
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