package com.sbot.common.vo;

import com.sbot.common.enums.ResultCode;
import com.sbot.common.exception.ProjectException;
import lombok.Data;

/**
 * 统一返回结果
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/26
 */
@Data
public class ResultVO<T> {
    /*返回代码*/
    Integer code;
    /*返回信息*/
    String message;
    /*返回数据*/
    T data;

    public static ResultVO success() {
        ResultVO<?> ret = new ResultVO<>();
        ret.code = ResultCode.success.getKey();
        ret.message = ResultCode.success.getValue();
        return ret;
    }

    public static <T> ResultVO success(T data) {
        ResultVO<T> ret = new ResultVO();
        ret.code = ResultCode.success.getKey();
        ret.message = ResultCode.success.getValue();
        ret.data = data;
        return ret;
    }

    public static ResultVO error() {
        ResultVO<?> ret = new ResultVO();
        ret.code = ResultCode.fail.getKey();
        ret.message = ResultCode.fail.getValue();
        return ret;
    }

    public static ResultVO exceptionError(Exception e) {
        ResultVO<?> ret = new ResultVO();
        ret.code = ResultCode.fail.getKey();
        ret.message = e.getMessage();
        return ret;
    }

    public static ResultVO exceptionError(ProjectException e) {
        ResultVO<?> ret = new ResultVO();
        ret.code = ResultCode.exceptionError.getKey();
        ret.message = e.getTypeMessage();
        return ret;
    }

    public static ResultVO exceptionError(RuntimeException e) {
        ResultVO<?> ret = new ResultVO();
        ret.code = ResultCode.fail.getKey();
        ret.message = e.getMessage();
        return ret;
    }

    public static ResultVO runtimeExceptionError(RuntimeException e) {
        ResultVO<?> ret = new ResultVO();
        ret.code = ResultCode.exceptionError.getKey();
        ret.message = e.getMessage();
        return ret;
    }

    public static ResultVO getResult(ResultCode resultCode) {
        ResultVO<?> ret = new ResultVO();
        ret.code = resultCode.getKey();
        ret.message = resultCode.getValue();
        return ret;
    }

    public static <T> ResultVO getResult(ResultCode resultCode, T data) {
        ResultVO<T> ret = new ResultVO();
        ret.code = resultCode.getKey();
        ret.message = resultCode.getValue();
        ret.data = data;
        return ret;
    }
}
