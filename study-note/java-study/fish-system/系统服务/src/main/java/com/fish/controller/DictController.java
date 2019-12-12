package com.fish.controller;

import com.fish.entity.FishDict;
import com.fish.service.DictService;
import components.base.BaseController;
import components.base.QueryVO;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/12
 */
@RestController
@RequestMapping("dict")
public class DictController implements BaseController<FishDict> {

    @Resource
    private DictService dictService;

    @PostMapping("select")
    @Override
    public Object select(QueryVO<FishDict> queryVO) throws Exception {
        return dictService.select(queryVO);
    }

    @PostMapping("save")
    @Override
    public Object save(@RequestBody List<FishDict> list) throws Exception {
        return dictService.save(list);
    }

    @PostMapping("delete")
    @Override
    public Object deleteByIds(@RequestBody List<String> idList) throws Exception {
        return dictService.deleteByIds(idList);
    }
}
