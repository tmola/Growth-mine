package com.code.modules.dict.service.impl;

import com.code.common.util.result.ServiceResult;
import com.code.modules.dict.entity.Dict;
import com.code.modules.dict.repository.DictRepository;
import com.code.modules.dict.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public Map deleteById(String id) {
        Map ret;
        Dict dict = dictRepository.delelteById(id);
        if (dict != null) {
            ret = ServiceResult.result(ServiceResult.Type.DELETE, 1, 1, 0);
            ret.put("deleteData", dict);
        } else {
            ret = ServiceResult.result(ServiceResult.Type.DELETE, 1, 0, 1);
        }
        return ret;
    }

    @Override
    public String getTextByCode(String catalog, String code) {
        return dictRepository.getTextByCode(catalog, code);
    }
}
