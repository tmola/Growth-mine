package com.design.common.vo;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 *
 * </p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/17 3:46
 */
@Data
public class CommonQuery<T> {

    boolean isPageable;
    Integer page;
    Integer pageSize;

    boolean isObject;
    T terms;
    String serchWord;

    public Pageable ofPage(){
        return PageRequest.of(page, pageSize);
    }
}
