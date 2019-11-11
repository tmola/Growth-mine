package com.code.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

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
public class ExceptionHandler {

    @ExceptionHandler()
}
