package com.sbot.modules.system.services;



import com.sbot.common.exception.ProjectException;
import com.sbot.common.vo.QueryVO;
import com.sbot.modules.system.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * 用户表Service接口
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
public interface SysUserService {

    Map save(List<SysUser> users) throws Exception ;

    Map delete(List<String> ids) throws Exception ;

    Map select(QueryVO<SysUser> conditions) throws Exception ;

    Map uploadExcelData(List<Object> datas) throws Exception ;

}
