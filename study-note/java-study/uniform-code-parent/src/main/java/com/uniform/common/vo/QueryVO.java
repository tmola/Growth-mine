package com.uniform.common.vo;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/4
 */
@Data
public class QueryVO<T> {
    /*true:实体字段查询 false:关键字查找*/
    Boolean objectQuery;
    /*true:分页查询 false:非分页查询*/
    Boolean pageQuery;

    Integer page;
    Integer pageSize;
    String keyword;
    T terms;

    public Pageable ofPage() {
        return PageRequest.of(page, pageSize);
    }
    public Pageable ofPage(List<Sort.Order> orders) {
        return PageRequest.of(page, pageSize, Sort.by(orders));
    }
}
