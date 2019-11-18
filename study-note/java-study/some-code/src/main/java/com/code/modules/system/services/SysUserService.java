package com.code.modules.system.services;


import com.code.modules.system.entity.SysUser;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.List;

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
