package com.code.modules.demo.service;

import com.code.common.exception.MyException;
import com.code.common.exception.MyRuntimeException;
import com.code.common.util.result.Result;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/11
 */
@Service
public class DealService {

    public void deal(Integer value) throws MyException {
        if (null == value)
            throw new MyException(Result.EType.invalidArguments);
    }

    public void deal_2(Integer value) throws MyRuntimeException {
        if (null == value)
            throw new MyRuntimeException(Result.EType.invalidArguments);
    }

    public void deal_3(Integer value) throws MyException, MyRuntimeException{
        if (null == value)
            throw new MyException(Result.EType.invalidArguments);
        else if (0 > value)
            throw new MyRuntimeException(Result.EType.invalidSignature);

    }
    public void deal_4(Integer value) throws Exception{
        if(0 == value){
            int s = 3 / value;
        }
    }
}
