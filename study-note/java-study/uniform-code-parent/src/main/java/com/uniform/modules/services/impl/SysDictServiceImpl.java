package com.uniform.modules.services.impl;


import com.sbot.common.base.BaseServiceOperator;
import com.sbot.common.utils.QueryStrategy;
import com.sbot.common.vo.QueryVO;
import com.sbot.modules.system.entity.SysDict;
import com.sbot.modules.system.repository.SysDictRepository;
import com.sbot.modules.system.services.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 字典表Service接口实现
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Service("SysDictService")
public class SysDictServiceImpl implements SysDictService, Serializable {

    @Autowired
    private SysDictRepository dictRepository;

    @Transactional
    @Modifying
    @Override
    public Map save(List<SysDict> dictList) throws Exception {
        return BaseServiceOperator.save(dictRepository, dictList);
    }

    @Transactional
    @Modifying
    @Override
    public Map deleteByIds(List<String> ids) throws Exception {
        return BaseServiceOperator.deleteByIds(dictRepository, ids);
    }

    @Override
    public Map select(QueryVO<SysDict> queryVO) throws Exception {
        QueryStrategy queryStrategy = new QueryStrategy();
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "sort"));
        return BaseServiceOperator.select(dictRepository, queryVO, queryStrategy, orders);
    }

    @Override
    public String getTextByCode(String catalog, String code) {
        return dictRepository.getText(catalog, code);
    }

    @Override
    public List<SysDict> getDictList(String catalog) {
        return dictRepository.getDictList(catalog);
    }

    @Override
    public List<String> getTextList(String catalog) {
        return dictRepository.getTextList(catalog);
    }

    @Override
    public String getCode(String catalog, String text) {
        return dictRepository.getCode(catalog, text);
    }
}