package com.design.common.exception;

import lombok.Data;

/**
 * 统一返回结果
 * <p>
 * @author jintingying
 * @version 1.0
 * @date 2019/11/26
 */
@Data
public class ResultVo<T> {
    Integer code;
    String  message;
    T data;
    public static ResultVo success(){
        ResultVo<?> ret = new ResultVo<>();
        ret.code = Integer.parseInt(ReturnMessage.success.getKey());
        ret.message = ReturnMessage.success.getTypeName();
        return ret;
    }

    public static <T> ResultVo success(T data){
        ResultVo<T> ret = new ResultVo();
        ret.code = Integer.parseInt(ReturnMessage.success.getKey());
        ret.message = ReturnMessage.success.getTypeName();
        ret.data = data;
        return ret;
    }

    public static ResultVo error(){
        ResultVo<?> ret = new ResultVo();
        ret.code = Integer.parseInt(ReturnMessage.fail.getKey());
        ret.message = ReturnMessage.fail.getTypeName();
        return ret;
    }
    public static ResultVo exceptionError(Exception e){
        ResultVo<?> ret = new ResultVo();
        ret.code = Integer.parseInt(ReturnMessage.fail.getKey());
        ret.message = e.getMessage();
        return ret;
    }
    public static ResultVo exceptionError(ProjectException e){
        ResultVo<?> ret = new ResultVo();
        ret.code = Integer.parseInt(ReturnMessage.fail.getKey());
        ret.message = e.getTypeMessage();
        return ret;
    }

    public static ResultVo runtimeExceptionError(RuntimeException e){
        ResultVo<?> ret = new ResultVo();
        ret.code = Integer.parseInt(ReturnMessage.fail.getKey());
        ret.message = e.getMessage();
        return ret;
    }

    public static ResultVo getResult(ReturnMessage returnMessage){
        ResultVo<?> ret = new ResultVo();
        ret.code = Integer.parseInt(returnMessage.getKey());
        ret.message = returnMessage.getTypeName();
        return ret;
    }

    public static <T> ResultVo getResult(ReturnMessage returnMessage, T data){
        ResultVo<T> ret = new ResultVo();
        ret.code = Integer.parseInt(returnMessage.getKey());
        ret.message = returnMessage.getTypeName();
        ret.data = data;
        return ret;
    }
}
