package com.uniform.common.exception.handler;

import com.uniform.common.exception.BusinessException;
import com.uniform.common.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    ResultVO exceptionHandler(Exception e){
       e.printStackTrace();
       if(e instanceof BusinessException)
           return ResultVO.businessExceptionError((BusinessException)e);
        return ResultVO.exceptionError(e);
    }
}
