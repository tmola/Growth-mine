package com.sbot.modules.system.controller;

import com.sbot.common.vo.QueryVO;
import com.sbot.common.vo.ResultVO;
import com.sbot.modules.system.entity.SysDict;
import com.sbot.modules.system.services.SysDictService;
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
        return ResultVO.success(dictService.save(dicts));
    }

    @PostMapping("select")
    public ResultVO select(@RequestBody QueryVO<SysDict> query) throws Exception{
        return ResultVO.success(dictService.select(query));
    }
}
