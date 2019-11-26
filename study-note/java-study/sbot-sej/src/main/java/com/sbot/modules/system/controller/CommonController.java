package com.sbot.modules.system.controller;

import com.sbot.common.utils.easyexcel.EasyExcelUtil;
import com.sbot.common.vo.QueryVO;
import com.sbot.common.vo.ResultVO;
import com.sbot.modules.system.entity.SysUser;
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
    @PostMapping("download")
    public void download(@RequestParam String filename,
                         @RequestParam String entityName,
                         @RequestParam String sheetName,
                         @RequestBody QueryVO<SysUser> query) throws Exception {
        EasyExcelUtil.exportDataByExcelFile(filename, sheetName, query, entityName);
    }

    @PostMapping("template")
    public void template(@RequestParam String fileName,
                         @RequestParam String entityName,
                         @RequestParam String sheetName) throws Exception {
        EasyExcelUtil.exportTemplateByExcelFile(fileName, sheetName, entityName);
    }

    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
    public ResultVO upload(@RequestParam String name,
                           @RequestParam("file") MultipartFile file) throws Exception {
        return EasyExcelUtil.importDataFromExcelFile(file, name, 0, 1);
    }

}
