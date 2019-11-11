package com.code.common.exception;

import com.code.common.util.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常处理
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/11
 */
@ControllerAdvice
@RestController
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        Result result = null;
        e.printStackTrace();
        if (e instanceof MyException) {
            result = Result.serviceException(((MyException) e).code, Result.EType.invalidArguments.getValue());
        } else {
            int index = e.getMessage().lastIndexOf("[");
            result = Result.serviceException(Result.EType.fail.getKey(),
                    index != -1 ? e.getMessage().substring(index + 1, e.getMessage().length() - 1).trim() : e.getMessage());
        }
        return result;
    }

    @ExceptionHandler(MyRuntimeException.class)
    public Result myRuntimeExceptionHandler(MyRuntimeException e) {
        e.printStackTrace();
        return Result.serviceException(e.getCode(), e.getMessage());
    }
}
