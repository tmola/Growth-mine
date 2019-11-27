package com.sbot.modules.system.services.impl;



import com.sbot.common.utils.OptRetMapUtil;
import com.sbot.common.utils.QueryStrategy;
import com.sbot.common.utils.ToolUtil;
import com.sbot.common.vo.QueryVO;
import com.sbot.modules.system.entity.SysDict;
import com.sbot.modules.system.repository.SysDictRepository;
import com.sbot.modules.system.services.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
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
@Service("SysDictService")
public class SysDictServiceImpl implements SysDictService, Serializable {

    @Autowired
    private SysDictRepository dictRepository;

    @Override
    public Map deleteByIds(List<String> ids) {
        int successCount = 0;
        if (ids == null)
            return OptRetMapUtil.optError();

        for (String id : ids) {
            SysDict dict = dictRepository.delelteById(id);
            if (dict != null) {
                successCount++;
            }
        }
        return OptRetMapUtil.deleteOptResult(ids.size(), successCount,
                ids.size() - successCount, null);
    }

    @Transactional
    @Modifying
    @Override
    public Map save(List<SysDict> dictList) {
        if (dictList == null)
            return OptRetMapUtil.optError();
        int successCount = 0;
        List<SysDict> fieldList = new ArrayList<>();
        for (SysDict dict : dictList) {
            if (ToolUtil.isNotEmpty(dict.getId())) {
                dict.setModifyUser(null);
                dict.setModifyTime(new Date());
            } else {
                dict.setId(ToolUtil.randomID35());
                dict.setDelFlag(0);
                dict.setCreateUser(null);
                dict.setCreateTime(new Date());
            }
            SysDict dictRet = dictRepository.saveAndFlush(dict);
            if (dictRet != null) successCount++;
            else fieldList.add(dict);
        }
        return OptRetMapUtil.saveOptResult(dictList.size(), successCount, fieldList.size(), fieldList);
    }

    @Override
    public Map select(QueryVO condition) {
        QueryStrategy queryStrategy = new QueryStrategy();
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "sort"));
        if(condition.isPageable()){
            Page<SysDict> page = dictRepository.findAll(QueryStrategy.ofAllLikeMatch(condition, queryStrategy), condition.ofPage(orders));
            return OptRetMapUtil.selectOptResult(page);
        }
        else{
            List<SysDict> list = dictRepository.findAll(QueryStrategy.ofAllLikeMatch(condition, queryStrategy), Sort.by(orders));
            return OptRetMapUtil.selectOptResult(list);
        }
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
}