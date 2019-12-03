package com.sbot.modules.system.controller;

import com.sbot.common.utils.DateTimeUitl;
import com.sbot.common.utils.easyexcel.EasyExcelUtil;
import com.sbot.common.vo.QueryVO;
import com.sbot.common.vo.ResultVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/26
 */
@RestController
@RequestMapping("public")
public class PublicController {
    @GetMapping("test")
    public void test() {
//        System.out.println(DateTimeUitl.getFieldFromDate(new Date(), 100));
        List all = new ArrayList();
        List sam = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            String s = DateTimeUitl.randomID(2  , 2);
            System.out.println(s);
            if(all.contains(s))
                sam.add(s);
            else all.add(s);
        }
        System.out.println(sam);
    }

    @ApiOperation(value = "公共excel数据导出", notes = "公共excel数据导出", httpMethod = "POST")
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

    @ApiOperation(value = "公共excel模板下载", notes = "公共excel模板下载", httpMethod = "POST")
    @PostMapping("template")
    public void template(@ApiParam(name = "filename", value = "下载保存文件名")
                         @RequestParam String filename,
                         @ApiParam(name = "entityName", value = "\"实体名\"")
                         @RequestParam String entityName,
                         @ApiParam(name = "sheetName", value = "Sheet表格名称")
                         @RequestParam String sheetName) throws Exception {
        EasyExcelUtil.exportTemplateByExcelFile(filename, sheetName, entityName);
    }

    @ApiOperation(value = "公共excel数据上传", notes = "公共excel数据上传", httpMethod = "POST")
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

    @PostMapping(value = "/upload2", headers = "content-type=multipart/form-data")
    public ResultVO upload2(@RequestParam String entityName,
                            @RequestParam String excelName,
                            @RequestParam("file") MultipartFile file) throws Exception {
        return EasyExcelUtil.importData(file, entityName, excelName);
    }

    @PostMapping("download2")
    public void download2(@RequestParam String filename,
                          @RequestParam String entityName,
                          @RequestParam String excelName,
                          @RequestParam String sheetName,
                          @RequestBody QueryVO query) throws Exception {
        EasyExcelUtil.exportData(filename, sheetName, entityName, excelName, query);
    }

    @PostMapping("template2")
    public void template2(@RequestParam String filename,
                          @RequestParam String entityName,
                          @RequestParam String sheetName,
                          @RequestParam String excelName) throws Exception {
        EasyExcelUtil.exportTemplate(filename, sheetName, entityName, excelName);
    }
}
