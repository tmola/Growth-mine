package com.code.modules.dict.service.impl;

import com.code.common.util.ObjectUtil;
import com.code.common.util.QueryPredicate;
import com.code.common.util.result.ServiceResult;
import com.code.modules.dict.entity.Dict;
import com.code.modules.dict.entity.vo.SearchVo;
import com.code.modules.dict.repository.DictRepository;
import com.code.modules.dict.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/13
 */
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictRepository dictRepository;

    @Override
    public Map deleteByIds(List<String> ids) {
        int successCount = 0;
        if (ids == null)
            return ServiceResult.resultError();

        for (String id : ids) {
            Dict dict = dictRepository.delelteById(id);
            if (dict != null) {
                successCount++;
            }
        }
        return ServiceResult.result(ServiceResult.Type.DELETE,
                ids.size(), successCount,
                ids.size() - successCount);
    }

    @Transactional
    @Modifying
    @Override
    public Map save(List<Dict> dictList) {
        if (dictList == null)
            return ServiceResult.resultError();
        int successCount = 0;
        List<Dict> fieldList = new ArrayList<>();
        for (Dict dict : dictList) {
            if (ObjectUtil.isNotEmpty(dict.getId())) {
                dict.setModifyUser(null);
                dict.setModifyTime(new Date());
            } else {
                dict.setId(ObjectUtil.randomID());
                dict.setDelFlag(0);
                dict.setCreateUser(null);
                dict.setCreateTime(new Date());
            }
            Dict dictRet = dictRepository.saveAndFlush(dict);
            if (dictRet != null) successCount++;
            else fieldList.add(dict);
        }
        return ServiceResult.result(ServiceResult.Type.SAVE, dictList.size(), successCount, fieldList.size(), fieldList);
    }

    @Override
    public Map get(SearchVo searchVo) {
        QueryPredicate queryPredicate = new QueryPredicate();
        queryPredicate.setIgnoredFieldsDefault();
        Page<Dict> page = dictRepository.findAll(QueryPredicate.of(searchVo, queryPredicate), searchVo.page());
        return ServiceResult.result(page.getContent());
    }

    @Override
    public String getTextByCode(String catalog, String code) {
        return dictRepository.getTextByCode(catalog, code);
    }

    @Override
    public List<Dict> getTextList(String catalog) {
        return dictRepository.getTextList(catalog);
    }
}
