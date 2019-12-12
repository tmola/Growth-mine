package fish.unit.response;

import fish.unit.exception.BusinessErrorCode;
import lombok.Data;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/11
 */
@Data
public class ResponseVO<T> {
    Integer code;
    String mesg;
    T data;
    Long time = System.currentTimeMillis();

    public static ResponseVO of(Enum re){
        ResponseVO responseVO = new ResponseVO();
        if (re instanceof ResponseCode.Success) {
            responseVO.setCode(((ResponseCode.Success) re).code);
            responseVO.setMesg(((ResponseCode.Success) re).text);
        } else if (re instanceof ResponseCode.Fail) {
            responseVO.setCode(((ResponseCode.Fail) re).code);
            responseVO.setMesg(((ResponseCode.Fail) re).text);
        }else if (re instanceof BusinessErrorCode) {
            responseVO.setCode(((BusinessErrorCode) re).getCode());
            responseVO.setMesg(((BusinessErrorCode) re).getMessage());
        }
        else {
            responseVO = of(ResponseCode.Fail.RET_ENUM);
        }
        return responseVO;
    }

    public static <T> ResponseVO of(Enum re, T data){
        ResponseVO responseVO = of(re);
        responseVO.setData(data);
        return responseVO;
    }
    public static ResponseVO success(){
        return of(ResponseCode.Success.SUCCESS);
    }

    public static <T> ResponseVO success(T data){
        return of(ResponseCode.Success.SUCCESS, data);
    }

    public static <T> ResponseVO fail(){
        return of(ResponseCode.Fail.FAIL);
    }

}
