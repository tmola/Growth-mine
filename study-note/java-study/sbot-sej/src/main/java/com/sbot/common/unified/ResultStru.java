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
public class ResultStru<T> {
    //    /*是否成功*/
//    Boolean success;
    /*返回代码*/
    Integer code;
    /*返回信息*/
    String messgae;
    /*返回数据*/
    T data;
    /*服务器时间戳*/
    Long timeMills = System.currentTimeMillis();


    public static ResultStru successful() {
        ResultStru resultStru = new ResultStru();
        resultStru.code = ResultCodeEnum.success.code;
        resultStru.messgae = ResultCodeEnum.success.info;
        return resultStru;
    }

    public static <T> ResultStru successful(T data) {
        ResultStru resultStru = new ResultStru();
        resultStru.code = ResultCodeEnum.success.code;
        resultStru.messgae = ResultCodeEnum.success.info;
        resultStru.data = data;
        return resultStru;
    }

    public static ResultStru failed() {
        ResultStru resultStru = new ResultStru();
        resultStru.code = ResultCodeEnum.faild.code;
        resultStru.messgae = ResultCodeEnum.faild.info;
        return resultStru;
    }

    public static ResultStru failed(ResultCodeEnum resultCodeEnum) {
        ResultStru resultStru = new ResultStru();
        resultStru.code = resultCodeEnum.code;
        resultStru.messgae = resultCodeEnum.info;
        return resultStru;
    }

    public static ResultStru failed(String messgae) {
        ResultStru resultStru = new ResultStru();
        resultStru.code = ResultCodeEnum.faild.code;
        resultStru.messgae = messgae;
        return resultStru;
    }

    public static ResultStru businessExceptionError(BusinessException e) {
        ResultStru resultStru = new ResultStru();
        resultStru.code = e.getCode();
        resultStru.messgae = e.getMessage();
        return resultStru;
    }

    public static ResultStru exceptionError(Exception e) {
        ResultStru resultStru = new ResultStru();
        resultStru.code = ResultCodeEnum.serviceException.code;
        resultStru.messgae = e.getMessage();
        return resultStru;
    }
}
