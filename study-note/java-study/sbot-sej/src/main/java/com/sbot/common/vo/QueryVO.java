package com.sbot.common.vo;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 统一查询内容
 * <p>
 *
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
    String searchWord;

    /*是否分页*/
    boolean isPageable;
    /*第几页*/
    Integer page;
    /*每页大小*/
    Integer pageSize;



    public QueryVO() {
    }

    public QueryVO(T data) {
        terms = data;
    }

    /**
     * 分页请求参数
     */
    public Pageable ofPage() {
        return PageRequest.of(page, pageSize);
    }
    public Pageable ofPage(List<Sort.Order> orders) {
        return PageRequest.of(page, pageSize, Sort.by(orders));
    }
}
