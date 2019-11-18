package com.code.modules.system.services;


import com.code.common.exception.MyException;
import com.code.modules.dict.entity.vo.SearchVo;
import com.code.modules.system.entity.SysDict;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;

import javax.jws.WebMethod;
import javax.jws.WebParam;
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

    Map get(SearchVo searchVo) throws Exception;

    String getTextByCode(String catalog, String code) throws Exception;

    List<SysDict> getTextList(String catalog) throws Exception;
}
