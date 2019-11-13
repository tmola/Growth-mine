package com.code.modules.dict.controller;

import com.code.common.dictAnnotation.DictResult;
import com.code.common.util.result.Result;
import com.code.modules.dict.entity.model.TestDict;
import com.code.modules.dict.repository.TestDictRepository;
import com.code.modules.dict.service.TestDictService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result getAll(){
        List<TestDict> list = service.getAll();

        return Result.success(Result.SType.success, list);
    }
}
