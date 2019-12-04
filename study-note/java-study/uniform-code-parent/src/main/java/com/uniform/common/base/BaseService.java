package com.uniform.common.base;

import com.sbot.common.vo.QueryVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/2 21:02
 */
public interface BaseService<T> {
    Map select(QueryVO<T> queryVO) throws Exception;
    Map save(List<T> records) throws Exception;
    Map deleteByIds(List<String> ids) throws Exception;
}
