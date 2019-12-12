package components.base;

import java.util.List;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/12
 */
public interface BaseController<T> {

    Object select(QueryVO<T> queryVO) throws Exception;

    Object save(List<T> list) throws Exception;

    Object deleteByIds(List<String> idList) throws Exception;
}
