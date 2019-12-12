package com.fish.service.impl;

import com.fish.entity.FishDict;
import com.fish.repository.DictRepository;
import com.fish.service.DictService;
import components.base.BaseServiceOperator;
import components.base.QueryVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/12
 */
@Service
public class DictServiceImpl implements DictService {
    @Resource
    private DictRepository dictRepository;

    @Override
    public Map select(QueryVO<FishDict> queryVO) throws Exception {
        return BaseServiceOperator.select(dictRepository, queryVO, null);
    }

    @Override
    public Map save(List<FishDict> records) throws Exception {
        return BaseServiceOperator.save(dictRepository, records);
    }

    @Override
    public Map deleteByIds(List<String> ids) throws Exception {
        for (String id : ids) {
            dictRepository.deleteById(id);
        }
        return null;
    }
}
