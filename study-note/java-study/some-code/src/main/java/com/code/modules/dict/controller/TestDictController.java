package com.code.modules.dict.controller;

import com.code.common.dictAnnotation.DictResult;
import com.code.common.util.excel.ExcelUtil;
import com.code.common.util.result.Result;
import com.code.common.util.result.ServiceResult;
import com.code.modules.dict.entity.model.TestDict;
import com.code.modules.dict.entity.vo.SearchVo;
import com.code.modules.dict.repository.TestDictRepository;
import com.code.modules.dict.service.TestDictService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/13
 */
@Api
@RestController
@RequestMapping("testDict")
public class TestDictController {

    @Autowired
    private TestDictService service;

    @DictResult
    @PostMapping("getAll")
    public Result getAll() throws Exception {
        List<TestDict> list = service.getAll();
        return Result.success(Result.SType.success, ServiceResult.result(list));
    }

    @DictResult
    @PostMapping("search")
    public Result searchAll(@RequestParam String search) throws Exception {
        List<TestDict> list = service.searchAll(search);
        return Result.success(Result.SType.success, ServiceResult.result(list));
    }
    @DictResult
    @PostMapping("searchByPage")
    public Result searchByPage(@RequestBody SearchVo searchVo) throws Exception {
        return Result.success(Result.SType.success,  service.searchAllByPage(searchVo));
    }

    @PostMapping(value = "downAll", produces = {"application/json; charset=UTF-8"})
    public void downAll(@RequestParam String filename) throws Exception {
        ExcelUtil.exportDataByExcelFile(service.getAll(), filename, "测试", null);
        return;
    }



}
