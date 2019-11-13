package com.code.modules.dict.service;

import com.code.common.util.result.ServiceResult;
import com.code.modules.dict.entity.Dict;

import java.util.Map;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/13
 */
public interface DictService {

    Map deleteById(String id);

    String getTextByCode(String catalog, String code);
}
