package com.uniform.modules.system.services;



import com.uniform.common.base.BaseService;
import com.uniform.modules.system.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * 用户表Service接口
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
public interface SysUserService extends BaseService<SysUser> {
    Map toSave(List<SysUser> users);
}
