package com.code.common.exception;

import com.code.common.util.result.Result;
import io.swagger.models.auth.In;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/11
 */
public class MyRuntimeException extends RuntimeException{
    Integer code;
    public MyRuntimeException(){
        super();
    }
    public MyRuntimeException(Result.EType type){
        super(type.getValue());
        this.code = type.getKey();
    }

    public Integer getCode() {
        return code;
    }
}
