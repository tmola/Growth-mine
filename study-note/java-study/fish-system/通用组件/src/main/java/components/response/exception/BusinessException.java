package components.response.exception;

import components.response.enums.BusinessErrorCode;
import lombok.Data;

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
