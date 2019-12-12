package com.fish.controller;

import com.fish.entity.FishDict;
import com.fish.service.DictService;
import components.base.BaseController;
import components.base.QueryVO;
import org.springframework.web.bind.annotation.PostMapping;
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
        return null;
    }

    @PostMapping("save")
    @Override
    public Object save(List<FishDict> list) throws Exception {
        return null;
    }

    @PostMapping("delete")
    @Override
    public Object deleteByIds(List<String> idList) throws Exception {
        return null;
    }
}
