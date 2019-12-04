package com.uniform.common.vo;

import com.uniform.common.exception.BusinessException;
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
public class ResultVO<T> {
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


    public static ResultVO successful() {
        ResultVO resultVO = new ResultVO();
        resultVO.code = ResultCode.success.code;
        resultVO.messgae = ResultCode.success.info;
        return resultVO;
    }

    public static <T> ResultVO successful(T data) {
        ResultVO resultVO = new ResultVO();
        resultVO.code = ResultCode.success.code;
        resultVO.messgae = ResultCode.success.info;
        resultVO.data = data;
        return resultVO;
    }

    public static ResultVO failed() {
        ResultVO resultVO = new ResultVO();
        resultVO.code = ResultCode.faild.code;
        resultVO.messgae = ResultCode.faild.info;
        return resultVO;
    }

    public static ResultVO failed(ResultCode resultCode) {
        ResultVO resultVO = new ResultVO();
        resultVO.code = resultCode.code;
        resultVO.messgae = resultCode.info;
        return resultVO;
    }

    public static ResultVO failed(String messgae) {
        ResultVO resultVO = new ResultVO();
        resultVO.code = ResultCode.faild.code;
        resultVO.messgae = messgae;
        return resultVO;
    }

    public static ResultVO businessExceptionError(BusinessException e) {
        ResultVO resultVO = new ResultVO();
        resultVO.code = e.getCode();
        resultVO.messgae = e.getMessage();
        return resultVO;
    }

    public static ResultVO exceptionError(Exception e) {
        ResultVO resultVO = new ResultVO();
        resultVO.code = ResultCode.serviceException.code;
        resultVO.messgae = e.getMessage();
        return resultVO;
    }
}
