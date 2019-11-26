package com.design.module.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.design.common.excel.ApplicationContextUtil;
import com.design.common.excel.ExcelUtil;
import com.design.common.util.ObjectUtil;
import com.design.common.util.result.ApiResult;
import com.design.common.vo.CommonQuery;
import com.design.module.system.entity.SysUser;
import com.design.module.system.entity.excel.UserExcel;
import com.design.module.system.services.SysUserService;
import io.swagger.annotations.Api;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.util.ArrayList;
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
        List datas = (List) map.get("list");
        List list = ObjectUtil.list2Object(new UserExcel(), datas);
        ExcelUtil.exportDataByExcelFile(list, downloadFile, "用户信息", 100);
    }

    @PostMapping("template")
    public void template(@RequestParam String excelClassName,
                         @RequestParam String sheetName,
                         @RequestParam String fileName) throws Exception {
        ExcelUtil.exportTemplateByExcelFile(excelClassName, sheetName, fileName);
    }

    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
    public ApiResult upload(@RequestParam String excelClassName,
                            @RequestParam String serviceBean,
                            @RequestParam("file") MultipartFile file) throws Exception {
        return ExcelUtil.importDataFromExcelFile(file, excelClassName, serviceBean, 0, 1);
    }


}
