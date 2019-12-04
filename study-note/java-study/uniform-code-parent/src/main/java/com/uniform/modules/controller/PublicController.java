package com.uniform.modules.controller;


import com.uniform.common.utils.DateTimeUitl;
import com.uniform.common.utils.easyexcel.EasyExcelUtil;
import com.uniform.common.vo.QueryVO;
import com.uniform.common.vo.ResultVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
                          @RequestParam String sheetName,
                          @RequestParam String entityName,
                          @RequestParam String excelName) throws Exception {
        EasyExcelUtil.exportTemplate(filename, sheetName, entityName, excelName);
    }
}
