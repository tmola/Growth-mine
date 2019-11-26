package com.sbot.common.handler;

import com.sbot.common.exception.ProjectException;
import com.sbot.common.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultVO exceptionHandler(Exception e) {
        e.printStackTrace();
        if(e instanceof ProjectException)
            return ResultVO.exceptionError((ProjectException)e);
        return ResultVO.exceptionError(e);
    }
}
