package com.fish.controller;

import com.fish.entity.FishDemo;
import components.response.config.IgnoreUnifiedResponse;
import components.response.enums.ResponseCode;
import components.response.vo.ResponseVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/12
 */
@RestController
public class FishTestController {

    @GetMapping("access1")
    public ResponseVO access(){
        return ResponseVO.success("访问成功");
    }

    @GetMapping("access2")
    public String access2(){
        return "访问成功";
    }

    @GetMapping("access3")
    public List access3(){
        List list = new ArrayList();
        FishDemo fishDemo = new FishDemo();
        fishDemo.setId("id");
        fishDemo.setSex("sex");
        fishDemo.setIncomeType("incomeType");
        list.add(fishDemo);
        return list;
    }

    @IgnoreUnifiedResponse
    @GetMapping("access4")
    public List access4(){
        List list = new ArrayList();
        FishDemo fishDemo = new FishDemo();
        fishDemo.setId("id");
        fishDemo.setSex("sex");
        fishDemo.setIncomeType("incomeType");
        list.add(fishDemo);
        return list;
    }


}
