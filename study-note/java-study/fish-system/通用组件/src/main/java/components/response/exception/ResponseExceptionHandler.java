package components.response.exception;

import components.response.enums.BusinessErrorCode;
import components.response.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/11
 */
@Slf4j
@RestControllerAdvice
public class ResponseExceptionHandler {
    /**
     * NoHandlerFoundException 404 异常处理
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseVO handlerNoHandlerFoundException(NoHandlerFoundException exception) {
        return ResponseVO.of(BusinessErrorCode.BUSINESS_ERROR);
    }

    /**
     * BindException 参数错误异常
     */
    @ExceptionHandler(BindException.class)
    public ResponseVO handleMethodArgumentNotValidException(BindException e) {
        return ResponseVO.of(BusinessErrorCode.PARAM_ERROR);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseVO handlerBusinessException(BusinessException e) {
        return ResponseVO.of(BusinessErrorCode.BUSINESS_ERROR);
    }

    /**
     * Exception 类捕获 500 异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseVO handlerException(Exception e) {
//        outPutErrorWarn(e.getClass(), BusinessErrorCode.BUSINESS_ERROR, e);
        return ResponseVO.of(BusinessErrorCode.BUSINESS_ERROR);
    }


    public void outPutErrorWarn(Class errorType, Enum secondaryErrorType, Throwable throwable) {
        log.warn("[{}] {}: {}", errorType.getSimpleName(), secondaryErrorType, throwable.getMessage());
    }

    public void outPutError(Class errorType, Enum secondaryErrorType, Throwable throwable) {
        log.error("[{}] {}: {}", errorType.getSimpleName(), secondaryErrorType, throwable.getMessage(),
                throwable);
    }

}
