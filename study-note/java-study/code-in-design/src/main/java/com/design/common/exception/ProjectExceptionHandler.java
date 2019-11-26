package com.design.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * 统一异常处理
 * <p>
 * @author jintingying
 * @version 1.0
 * @date 2019/11/26
 */
@RestController
@ControllerAdvice
public class ProjectExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResultVo exceptionHandler(Exception e) {
        e.printStackTrace();
        if(e instanceof ProjectException)
            return ResultVo.exceptionError((ProjectException)e);
        return ResultVo.exceptionError(e);
    }
}
