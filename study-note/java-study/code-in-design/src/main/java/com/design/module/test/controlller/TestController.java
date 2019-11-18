package com.design.module.test.controlller;

import com.design.common.util.result.ApiResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/17 3:38
 */
@Api(value = "项目测试", tags = "运行测试")
@RestController
public class TestController {

    @GetMapping("run")
    public ApiResult testRun(){
        return ApiResult.success();
    }
}
