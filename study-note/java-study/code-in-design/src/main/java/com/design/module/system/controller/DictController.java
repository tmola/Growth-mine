package com.design.module.system.controller;

import com.design.common.util.result.ApiResult;
import com.design.common.vo.CommonQuery;
import com.design.module.system.entity.SysDict;
import com.design.module.system.entity.SysUser;
import com.design.module.system.services.SysDictService;
import com.design.module.system.services.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/22
 */
@Api
@RestController
@RequestMapping("dict")
public class DictController {
    @Autowired
    private SysDictService dictService;

    @PostMapping("save")
    public ApiResult save(@RequestBody List<SysDict> dicts) throws Exception{
        return ApiResult.success(dictService.save(dicts));
    }

    @PostMapping("select")
    public ApiResult select(@RequestBody CommonQuery<SysDict> query) throws Exception{
        return ApiResult.success(dictService.get(query));
    }
}
