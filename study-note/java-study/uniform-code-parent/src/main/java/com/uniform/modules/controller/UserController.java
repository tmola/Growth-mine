package com.uniform.modules.controller;


import com.uniform.common.vo.QueryVO;
import com.uniform.common.vo.ResultVO;
import com.uniform.modules.services.SysUserService;
import com.uniform.modules.entity.SysUser;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/19
 */
@Api
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private SysUserService userService;

    @PostMapping("save")
    public ResultVO save(@RequestBody List<SysUser> users) throws Exception {
        return ResultVO.successful(userService.save(users));
    }

    @PostMapping("select")
    public ResultVO select(@RequestBody QueryVO<SysUser> query) throws Exception {
        return ResultVO.successful(userService.select(query));
    }

    @PostMapping("delete")
    public ResultVO delete(@RequestBody List<String> ids) throws Exception {
        return ResultVO.successful(userService.deleteByIds(ids));
    }
}
