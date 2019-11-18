package com.design.module.system.services.impl;


import com.design.common.util.ObjectUtil;
import com.design.common.util.QueryPredicate;
import com.design.common.util.result.OptResult;
import com.design.common.vo.QueryTermsVO;
import com.design.module.system.entity.SysDict;
import com.design.module.system.repository.SysDictRepository;
import com.design.module.system.services.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 字典表Service接口实现
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Service
public class SysDictServiceImpl implements SysDictService, Serializable {

    @Autowired
    private SysDictRepository dictRepository;

    @Override
    public Map deleteByIds(List<String> ids) {
        int successCount = 0;
        if (ids == null)
            return OptResult.optError();

        for (String id : ids) {
            SysDict dict = dictRepository.delelteById(id);
            if (dict != null) {
                successCount++;
            }
        }
        return OptResult.deleteResult(ids.size(), successCount,
                ids.size() - successCount, null);
    }

    @Transactional
    @Modifying
    @Override
    public Map save(List<SysDict> dictList) {
        if (dictList == null)
            return OptResult.optError();
        int successCount = 0;
        List<SysDict> fieldList = new ArrayList<>();
        for (SysDict dict : dictList) {
            if (ObjectUtil.isNotEmpty(dict.getId())) {
                dict.setModifyUser(null);
                dict.setModifyTime(new Date());
            } else {
                dict.setId(ObjectUtil.randomID());
                dict.setDelFlag(0);
                dict.setCreateUser(null);
                dict.setCreateTime(new Date());
            }
            SysDict dictRet = dictRepository.saveAndFlush(dict);
            if (dictRet != null) successCount++;
            else fieldList.add(dict);
        }
        return OptResult.saveResult(dictList.size(), successCount, fieldList.size(), fieldList);
    }

    @Override
    public Map get(QueryTermsVO searchVo) {
        QueryPredicate queryPredicate = new QueryPredicate();
        queryPredicate.setIgnoredFieldsDefault();
        Page<SysDict> page = dictRepository.findAll(QueryPredicate.of(searchVo, queryPredicate), searchVo.ofPage());
        return OptResult.selectResult(page);
    }

    @Override
    public String getTextByCode(String catalog, String code) {
        return dictRepository.getTextByCode(catalog, code);
    }

    @Override
    public List<SysDict> getTextList(String catalog) {
        return dictRepository.getTextList(catalog);
    }
}