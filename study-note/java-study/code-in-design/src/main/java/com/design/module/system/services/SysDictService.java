package com.design.module.system.services;

import com.design.common.vo.CommonQuery;
import com.design.module.system.entity.SysDict;

import java.util.List;
import java.util.Map;

/**
 * 字典表Service接口
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
public interface SysDictService {
    Map deleteByIds(List<String> ids) throws Exception;

    Map save(List<SysDict> dictList) throws Exception;

    Map get(CommonQuery searchVo) throws Exception;

    String getTextByCode(String catalog, String code) throws Exception;

    List<SysDict> getDictList(String catalog);

    List<String> getTextList(String catalog);
}
