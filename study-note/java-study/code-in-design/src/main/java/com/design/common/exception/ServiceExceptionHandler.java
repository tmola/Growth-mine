package com.design.common.exception;

import com.design.common.util.result.ApiResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/17 3:18
 */
@RestController
@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ApiResult exceptionHandler(Exception e) {
        ApiResult result;
        e.printStackTrace();
        if (e instanceof ServiceException) {
            result = ApiResult.error(((ServiceException) e).getType());
        } else {
            int index = e.getMessage().lastIndexOf("[");
            result = ApiResult.error(index != -1 ? e.getMessage().substring(index + 1, e.getMessage().length() - 1).trim() : e.getMessage());
        }
        return result;
    }
}
