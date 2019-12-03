package com.sbot.common.exception;

import com.sbot.common.enums.ResultCode;
import lombok.Data;

/**
 * 统一异常处理：自定义异常
 * <p>
 * @author jintingying
 * @version 1.0
 * @date 2019/11/26
 */
@Data
public class ProjectException extends Exception {
    private Integer code;
    private String  typeMessage;
    public ProjectException(){
        super();
    }
    public ProjectException(ResultCode resultCode){
        super(resultCode.getValue());
        code=  resultCode.getKey();
        typeMessage = resultCode.getValue();
    }

    public ProjectException(String message){
        super(message);
        code= ResultCode.fail.getKey();
        typeMessage = message;
    }
}
