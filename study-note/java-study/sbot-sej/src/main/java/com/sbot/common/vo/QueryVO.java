package com.sbot.common.vo;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * 统一查询内容
 * <p>
 * @author jintingying
 * @version 1.0
 * @date 2019/11/26
 */
@Data
public class QueryVO<T> {

    /*查询方式*/
    boolean isObject;
    /*实体*/
    T terms;
    /*关键字*/
    String serchWord;

    /*是否分页*/
    boolean isPageable;
    /*第几页*/
    Integer page;
    /*每页大小*/
    Integer pageSize;

    /**
     * 分页请求参数
     */
    public Pageable ofPage(){
        return PageRequest.of(page, pageSize);
    }
}
