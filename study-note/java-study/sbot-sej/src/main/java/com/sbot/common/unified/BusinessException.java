package com.sbot.common.unified;

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
        this.code  = ResultCodeEnum.faild.code;
        this.message = message;
    }
    public BusinessException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.info);
        this.code  = ResultCodeEnum.faild.code;
        this.message = resultCodeEnum.info;
    }
}
