package com.code.modules.dict.entity.vo;

import com.code.modules.dict.entity.Dict;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/14
 */
@Data
public class SearchVo<T> {
    private Integer type;

    private Integer page;

    private Integer pageSize;

    private String search;

    private T prod;

    public Pageable page(){
        return PageRequest.of(page, pageSize);
    }
}
