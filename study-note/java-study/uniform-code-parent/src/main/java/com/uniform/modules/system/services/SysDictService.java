package com.uniform.modules.system.services;


import com.uniform.common.base.BaseService;
import com.uniform.modules.system.entity.SysDict;

import java.util.List;

/**
 * 字典表Service接口
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
public interface SysDictService extends BaseService<SysDict> {
    String getTextByCode(String catalog, String code) throws Exception;

    List<SysDict> getDictList(String catalog);

    List<String> getTextList(String catalog);

    String getCode(String catalog, String text);
}
