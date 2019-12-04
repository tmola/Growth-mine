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
    /*ture:实体字段查询 false:关键字查找*/
    Boolean objectQuery;
    /*ture:分页查询 false:非分页查询*/
    Boolean pageQuery;

    Integer page;
    Integer pageSize;
    String keyword;
    T terms;

    Pageable ofPage() {
        return PageRequest.of(page, pageSize);
    }
    Pageable ofSortPage(List<Sort.Order> orders) {
        return PageRequest.of(page, pageSize, Sort.by(orders));
    }
}
