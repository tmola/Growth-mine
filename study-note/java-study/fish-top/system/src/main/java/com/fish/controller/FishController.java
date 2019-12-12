package com.fish.controller;


import annotation.TransDict;
import config.Author;
import fish.unit.exception.BusinessErrorCode;
import fish.unit.exception.BusinessException;
import fish.unit.response.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/11
 */
@RestController
public class FishController {

    @Resource
    private Author author;
    // 编译错误：required a bean of type 'config.Author' that could not be found.
    // 此时的Author在utils Module下
    // SpringBoot运行时所加载的包是Application.java本包及其子包的代码。所以根本扫描不到其他包，你怎么改注解都是错误的。
    // 需在启动类上加上@ComponentScan(...)

    @GetMapping("access")
    ResponseVO testAccess() {
        return ResponseVO.success("访问成功");
    }

    @GetMapping("r1")
    Object testResponse1() {
        return "1";
    }

    @GetMapping("r2")
    Object testResponse2() {
        List list = new ArrayList<>();
        //        list.add(new ResponseCode()); // 不能使用无法jackson转换的类型。。。。
        // Resolved [org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class fish.unit.response.ResponseCode];
        // nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException:
        // No serializer found for class fish.unit.response.ResponseCode and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)
        // (through reference chain: fish.unit.response.ResponseVO["data"]->java.util.ArrayList[0])]
        list.add(1);
        list.add(2);
        return list;
    }


//    @TransDict
    @GetMapping("author")
    ResponseVO testAuthor() {
        return ResponseVO.success(author);
    }

    @GetMapping("exception")
    ResponseVO testException() throws BusinessException {
        throw new BusinessException(BusinessErrorCode.BUSINESS_ERROR);
//       return ResponseVO.success();
    }


}
