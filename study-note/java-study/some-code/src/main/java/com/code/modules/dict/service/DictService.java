package com.code.modules.dict.service;

import com.code.modules.dict.entity.Dict;
import com.code.modules.dict.entity.vo.SearchVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/13
 */
public interface DictService {

    Map deleteByIds(List<String> ids) throws Exception;

    Map save(List<Dict> dictList) throws Exception;

    Map get(SearchVo searchVo) throws Exception;

    String getTextByCode(String catalog, String code) throws Exception;

    List<Dict> getTextList(String catalog) throws Exception;
}
