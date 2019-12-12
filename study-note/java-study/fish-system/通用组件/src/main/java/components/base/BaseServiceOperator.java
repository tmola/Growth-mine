package components.base;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import utils.IDUtil;

import java.lang.reflect.Method;
import java.util.*;


public class BaseServiceOperator {
    public static <T> Map save(BaseRepository<T> repository, List<T> records) throws Exception {
        if (null == records || records.isEmpty()) return OptRetMapUtil.optError("保存的数据不能为空");
        Integer success = 0;
        Integer field = 0;
        Class clz = records.get(0).getClass();
        Method getId = clz.getMethod("getId");
        Method setId = clz.getMethod("setId", String.class);
        Method setDelFlag = clz.getMethod("setDelFlag", Integer.class);
        Method setCreateTime = clz.getMethod("setCreateTime", Date.class);
        Method setCreateUser = clz.getMethod("setCreateUser", String.class);
        Method setModifyTime = clz.getMethod("setModifyTime", Date.class);
        Method setModifyUser = clz.getMethod("setModifyUser", String.class);
        List<T> fieldList = new ArrayList<>();
        for (T record : records) {
            if (Objects.nonNull(setId))
                if (Objects.nonNull(getId.invoke(record))) {
                    if (Objects.nonNull(setId))
                        setId.invoke(record, IDUtil.baseUID());
                    if (Objects.nonNull(setDelFlag))
                        setDelFlag.invoke(record, 0);
                    if (Objects.nonNull(setCreateTime))
                        setCreateTime.invoke(record, new Date());
                } else {
                    if (Objects.nonNull(setModifyTime))
                        setModifyTime.invoke(record, new Date());
                }
            T savedRecord;
            try {
                savedRecord = repository.saveAndFlush(record);
            } catch (Exception e) {
                field++;
                fieldList.add(record);
                continue;
            }
            if (Objects.isNull(savedRecord)) {
                field++;
                fieldList.add(record);
            } else success++;
        }
        return OptRetMapUtil.saveOptResult(records.size(), success, field, fieldList);
    }

    public static <T> Map deleteByIds(BaseRepository<T> repository, List<String> ids) throws Exception {
        if (ids == null || ids.isEmpty()) return OptRetMapUtil.optError("删除的数据不能为空");
        Integer total = ids.size();
        Integer success = 0;
        Integer field = 0;
        List<String> fieldList = new ArrayList<>();
        for (String id : ids) {
            int deleted;
            try {
                deleted = repository.deleteRecordById(id);
            } catch (Exception e) {
                field++;
                fieldList.add(id);
                continue;
            }
            if (deleted <= 0) {
                field++;
                fieldList.add(id);
            } else success++;
        }
        return OptRetMapUtil.deleteOptResult(total, success, field, fieldList);
    }

    public static <T> Map select(BaseRepository<T> repository, QueryVO<T> queryVO, QueryStrategy queryStrategy, List<Sort.Order> orders) throws Exception {
        if (null == queryStrategy) return select(repository, queryVO, orders);
        if (queryVO.getPageQuery()) {
            Page page = repository.findAll(QueryStrategy.ofAllLikeMatch(queryVO, queryStrategy), queryVO.ofPage(orders));
            return OptRetMapUtil.selectOptResult(page);
        } else {
            List list = repository.findAll(QueryStrategy.ofAllLikeMatch(queryVO, queryStrategy), Sort.by(orders));
            return OptRetMapUtil.selectOptResult(list);
        }
    }

    public static <T> Map select(BaseRepository<T> repository, QueryVO<T> queryVO, List<Sort.Order> orders) throws Exception {
        List orderList = new ArrayList();
        if (queryVO.getDescOrderBy() != null) {
            for (String descField : queryVO.getDescOrderBy()) {
                orderList.add(new Sort.Order(Sort.Direction.DESC, descField));
            }
        }

        if (queryVO.getAscOrderBy() != null) {
            for (String ascField : queryVO.getAscOrderBy()) {
                orderList.add(new Sort.Order(Sort.Direction.ASC, ascField));
            }
        }
        if (orders != null)
            orderList.addAll(orders);
        if (queryVO.getPageQuery()) {
            Page page = repository.findAll(queryVO.ofPage(orderList));
            return OptRetMapUtil.selectOptResult(page);
        } else {
            List list = repository.findAll(Sort.by(orderList));
            return OptRetMapUtil.selectOptResult(list);
        }
    }
}
