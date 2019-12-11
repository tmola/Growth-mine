package fish.unit.exception;

import lombok.Data;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/11
 */
@Data
public class BusinessException  extends Exception{
    private Integer code;
    private String message;
    public BusinessException(){
        super();
    }
    public BusinessException(BusinessErrorCode errorCode){
        super(errorCode.getMessage());
        code = errorCode.getCode();
        message = errorCode.getMessage();
    }
}
