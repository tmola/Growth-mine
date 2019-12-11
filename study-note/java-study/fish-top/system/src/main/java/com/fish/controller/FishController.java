package com.fish.controller;

import fish.unit.exception.BusinessErrorCode;
import fish.unit.exception.BusinessException;
import fish.unit.response.ResponseCode;
import fish.unit.response.ResponseVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/11
 */
@RestController
public class FishController {


    @GetMapping("access")
    ResponseVO testAccess(){
        return ResponseVO.success("访问成功");
    }

    @GetMapping("r1")
    Object testResponse1(){
        return "1";
    }

    @GetMapping("r2")
    Object testResponse2(){
        List list = new ArrayList<>();
 //        list.add(new ResponseCode()); // 不能使用无法转换为jackson的类型。。。。
        // Resolved [org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class fish.unit.response.ResponseCode];
        // nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException:
        // No serializer found for class fish.unit.response.ResponseCode and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)
        // (through reference chain: fish.unit.response.ResponseVO["data"]->java.util.ArrayList[0])]
        list.add(1);
        list.add(2);
        return list;
        }

    @GetMapping("exception")
    ResponseVO testException() throws BusinessException {
       throw new BusinessException(BusinessErrorCode.BUSINESS_ERROR);
//       return ResponseVO.success();
    }

}
