package com.design.common.exception;

import com.design.common.enums.ResultType;

/**
 * <p>
 *
 * </p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/17 3:15
 */
public class ServiceException extends Exception{
    private Integer code;
    private ResultType type;
    public ServiceException(){
        super();
    }
    public ServiceException(String detailMsg){
        super(detailMsg);
    }

    public ServiceException(ResultType type){
        super(type.getValue());
        this.code = type.getKey();
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }
    public ResultType getType() {
        return type;
    }
}
