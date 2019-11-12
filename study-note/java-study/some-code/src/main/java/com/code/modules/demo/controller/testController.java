package com.code.modules.demo.controller;

import com.code.common.exception.MyException;
import com.code.common.exception.MyExceptionHandler;
import com.code.common.exception.MyRuntimeException;
import com.code.common.util.result.Result;
import com.code.modules.demo.service.DealService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.callback.Callback;
import java.util.concurrent.Callable;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/11
 */
@Slf4j
@Api(tags = "测试接口", value = "test")
@RestController
@RequestMapping("test")
public class testController {

    @Autowired
    private DealService dealService;

    @GetMapping(value = "run")
    public Result run() {
        log.info("run");
        return Result.success();
    }

    @GetMapping(value = "asyncRun")
    public Callable<Result> asyncRun(){
        return  ()->run();
    }

    @GetMapping(value = "exception")
    public Result exception(@RequestParam(required = false) Integer value) {
        Result result;
        try {
            dealService.deal(value);
            result = Result.success();
        } catch (MyException e) {
            result = Result.serviceException(e.getCode(), e.getMessage());
        }
        return result;
    }

    @GetMapping(value = "exception2")
    public Result exception2(@RequestParam(required = false) Integer value) {
        Result result;
        try {
            dealService.deal_2(value);
            result = Result.success();
        } catch (MyRuntimeException e) {
            result = Result.serviceException(e.getCode(), e.getMessage());
        }
        return result;
    }

    @GetMapping(value = "exception3")
    public Result exception3(@RequestParam(required = false) Integer value) throws MyException, MyRuntimeException{
        dealService.deal_3(value);
        return  Result.success();
    }

    @GetMapping(value = "exception4")
    public Result exception4(@RequestParam(required = false) Integer value) throws Exception{
        dealService.deal_4(value);
        return  Result.success();
    }


}
