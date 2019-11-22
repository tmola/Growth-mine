package com.design.module.system.controller;

import com.design.common.util.ExcelUtil;
import com.design.common.util.ObjectUtil;
import com.design.common.util.result.ApiResult;
import com.design.common.util.result.OptResult;
import com.design.common.vo.CommonQuery;
import com.design.module.system.entity.SysUser;
import com.design.module.system.entity.excel.UserExcel;
import com.design.module.system.services.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ApiResult save(@RequestBody List<SysUser> users) throws Exception {
        return ApiResult.success(userService.save(users));
    }

    @PostMapping("select")
    public ApiResult select(@RequestBody CommonQuery<SysUser> query) throws Exception {
        return ApiResult.success(userService.select(query));
    }

    @PostMapping("download")
    public void download(@RequestParam String downloadFile, @RequestBody CommonQuery<SysUser> query) throws Exception {
        Map map = userService.select(query);
        List<SysUser> datas = (List<SysUser>) map.get("list");
        List list = ObjectUtil.list2Object(new UserExcel(), datas);
        ExcelUtil.exportDataByExcelFile(list, downloadFile, "用户信息", 100);
    }


}
