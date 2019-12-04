package com.uniform.common.exception;

import com.uniform.common.enums.ResultCode;
import lombok.Data;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/4
 */
@Data
public class BusinessException extends Exception {
    private Integer code;
    private String message;
    public BusinessException(){
        super();
    }
    public BusinessException(String message){
        super(message);
        this.code  = ResultCode.faild.code;
        this.message = message;
    }
    public BusinessException(ResultCode resultCode){
        super(resultCode.info);
        this.code  = ResultCode.faild.code;
        this.message = resultCode.info;
    }
}
