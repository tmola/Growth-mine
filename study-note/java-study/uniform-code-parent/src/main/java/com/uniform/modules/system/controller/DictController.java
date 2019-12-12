package com.uniform.modules.system.controller;


import com.uniform.common.vo.QueryVO;
import com.uniform.common.vo.ResultVO;
import com.uniform.modules.system.services.SysDictService;
import com.uniform.modules.system.entity.SysDict;
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
    public ResultVO save(@RequestBody List<SysDict> dicts) throws Exception{
        return ResultVO.successful(dictService.save(dicts));
    }

    @PostMapping("select")
    public ResultVO select(@RequestBody QueryVO<SysDict> query) throws Exception{
        return ResultVO.successful(dictService.select(query));
    }

    @PostMapping("delete")
    public ResultVO delete(@RequestBody List<String> ids) throws Exception {
        return ResultVO.successful(dictService.deleteByIds(ids));
    }
}
