package com.code.modules.system.services.impl;


import com.code.modules.system.entity.SysUser;
import com.code.modules.system.repository.SysUserRepository;
import com.code.modules.system.services.SysUserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;

/**
 * 用户表Service接口实现
 *
 * @author ${author}
 * @version $v: ${version}, $time:${datetime} Exp $
 */
@Service
public class SysUserServiceImpl implements SysUserService, Serializable {
    @Autowired
    private SysUserRepository userRepository;

    @Override
    public SysUser save(SysUser user) {
        return userRepository.save(user);
    }

    @Override
    public SysUser findUserById(String id) {
        Optional<SysUser> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
       return null;
    }

    @Override
    public SysUser findByUsername(String name) {
        SysUser user = userRepository.getByName(name);
        return user;
    }
}