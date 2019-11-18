package com.design.module.system.services;


import com.code.modules.system.entity.SysUser;

/**
 * 用户表Service接口
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
public interface SysUserService {
    SysUser save(SysUser user);

    SysUser findUserById(String id);

    SysUser findByUsername(String name);
}
