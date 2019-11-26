package com.design.common.exception;

import lombok.Data;

/**
 * <p>
 *
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
    public ProjectException(ReturnMessage returnMessage){
        super();
        code=  Integer.parseInt(returnMessage.getKey());
        typeMessage = returnMessage.getTypeName();
    }
}
