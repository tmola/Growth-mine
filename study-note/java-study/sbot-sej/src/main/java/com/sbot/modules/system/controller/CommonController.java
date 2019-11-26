package com.sbot.modules.system.controller;

import com.sbot.common.utils.easyexcel.EasyExcelUtil;
import com.sbot.common.vo.QueryVO;
import com.sbot.common.vo.ResultVO;
import com.sbot.modules.system.entity.SysUser;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/26
 */
@RestController
@RequestMapping("common")
public class CommonController {
    @ApiOperation( value = "公共excel数据导出", notes = "公共excel数据导出", httpMethod = "POST" )
    @PostMapping("download")
    public void download(@ApiParam(name = "filename", value = "下载保存文件名")
                         @RequestParam String filename,
                         @ApiParam(name = "entityName", value = "\"实体名\"")
                         @RequestParam String entityName,
                         @ApiParam(name = "sheetName", value = "Sheet表格名称")
                         @RequestParam String sheetName,
                         @ApiParam(name = "query", value = "下载内容查询参数")
                         @RequestBody QueryVO query) throws Exception {
        EasyExcelUtil.exportDataByExcelFile(filename, sheetName, entityName, query);
    }

    @ApiOperation( value = "公共excel模板下载", notes = "公共excel模板下载", httpMethod = "POST" )
    @PostMapping("template")
    public void template(@ApiParam(name = "filename", value = "下载保存文件名")
                         @RequestParam String filename,
                         @ApiParam(name = "entityName", value = "\"实体名\"")
                         @RequestParam String entityName,
                         @ApiParam(name = "sheetName", value = "Sheet表格名称")
                         @RequestParam String sheetName) throws Exception {
        EasyExcelUtil.exportTemplateByExcelFile(filename, sheetName, entityName);
    }

    @ApiOperation( value = "公共excel数据上传", notes = "公共excel数据上传", httpMethod = "POST" )
    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
    public ResultVO upload(@ApiParam(name = "entityName", value = "\"实体名\"")
                           @RequestParam String entityName,
                           @ApiParam(name = "sheet", value = "从第几个sheet开始")
                           @RequestParam Integer sheet,
                           @ApiParam(name = "row", value = "从第几行开始")
                           @RequestParam Integer row,
                           @RequestParam("file") MultipartFile file) throws Exception {
        return EasyExcelUtil.importDataFromExcelFile(file, entityName, sheet, row);
    }

}
