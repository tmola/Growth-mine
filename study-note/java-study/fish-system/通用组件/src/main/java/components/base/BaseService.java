package components.base;

import java.util.List;
import java.util.Map;

public interface BaseService<T> {
    Map select(QueryVO<T> queryVO) throws Exception;
    Map save(List<T> records) throws Exception;
    Map deleteByIds(List<String> ids) throws Exception;
}
