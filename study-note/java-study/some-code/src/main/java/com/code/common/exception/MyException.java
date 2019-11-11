package com.code.common.exception;

import com.code.common.util.result.Result;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/11
 */
public class MyException extends Exception{

    Integer code;

    public  MyException(){
        super();
    }
    public  MyException(String detailMsg){
        super(detailMsg);
    }

    public  MyException(Result.EType type){
        super(type.getValue());
        this.code = type.getKey();
    }

    public Integer getCode() {
        return code;
    }

}
