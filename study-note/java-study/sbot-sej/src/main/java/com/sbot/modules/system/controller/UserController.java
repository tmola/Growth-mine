package com.sbot.modules.system.controller;

import com.sbot.common.utils.ToolUtil;
import com.sbot.common.utils.easyexcel.EasyExcelUtil;
import com.sbot.common.vo.QueryVO;
import com.sbot.common.vo.ResultVO;
import com.sbot.modules.system.entity.SysUser;
import com.sbot.modules.system.entity.excel.UserExcel;
import com.sbot.modules.system.services.SysUserService;
import io.swagger.annotations.Api;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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
        return ResultVO.success(userService.save(users));
    }

    @PostMapping("select")
    public ResultVO select(@RequestBody QueryVO<SysUser> query) throws Exception {
        return ResultVO.success(userService.select(query));
    }



}
