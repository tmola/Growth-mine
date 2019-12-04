package com.sbot.common.unified;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/4
 */
@RestControllerAdvice
public class BusinessExceptionHandlerController {
    @ExceptionHandler(Exception.class)
    ResultStru exceptionHandler(Exception e){
       e.printStackTrace();
       if(e instanceof BusinessException)
           return ResultStru.businessExceptionError((BusinessException)e);
        return ResultStru.exceptionError(e);
    }
}
