package com.design.common.util.result;

import com.design.common.enums.ResultType;
import com.design.common.util.DateUtil;
import lombok.Data;

/**
 * <p>
 * 统一返回结果
 * </p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/17 2:31
 */
@Data
public class ApiResult<T> {
    /**
     * 返回代码
     */
    private Integer code;
    /**
     * 返回信息说明
     */
    private String  message;
    /**
     * 返回信息数据
     */
    private T data;
    /**
     * 日期
     */
    private String time = DateUtil.getCurrentDateTime();

    public static ApiResult success(){
        ApiResult result = new ApiResult();
        result.setCode(ResultType.success.getKey());
        result.setMessage(ResultType.success.getValue());
        return  result;
    }
    public static <T> ApiResult success(T data){
        ApiResult result = new ApiResult();
        result.setCode(ResultType.success.getKey());
        result.setMessage(ResultType.success.getValue());
        result.setData(data);
        return  result;
    }
    public static ApiResult error(){
        ApiResult result = new ApiResult();
        result.setCode(ResultType.fail.getKey());
        result.setMessage(ResultType.fail.getValue());
        return  result;
    }
    public static ApiResult error(ResultType type){
        ApiResult result = new ApiResult();
        result.setCode(type.getKey());
        result.setMessage(type.getValue());
        return  result;
    }
    public static ApiResult error(String message){
        ApiResult result = new ApiResult();
        result.setCode(ResultType.fail.getKey());
        result.setMessage(message);
        return  result;
    }

}
