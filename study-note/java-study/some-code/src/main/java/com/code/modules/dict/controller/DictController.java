package com.code.modules.dict.controller;

import com.code.common.util.result.Result;
import com.code.modules.dict.entity.Dict;
import com.code.modules.dict.entity.vo.SearchVo;
import com.code.modules.dict.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/14
 */
@RestController
@RequestMapping("dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @PostMapping("get")
    public Result get(@RequestBody SearchVo<Dict> searchVo) throws Exception{
        return Result.success(Result.SType.success, dictService.get(searchVo));
    }
}
