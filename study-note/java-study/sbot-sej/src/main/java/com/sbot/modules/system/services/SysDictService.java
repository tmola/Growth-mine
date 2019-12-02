package com.sbot.modules.system.services;


import com.sbot.common.base.BaseService;
import com.sbot.modules.system.entity.SysDict;

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
}
